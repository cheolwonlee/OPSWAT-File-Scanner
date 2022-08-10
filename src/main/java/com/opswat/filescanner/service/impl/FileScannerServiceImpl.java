package com.opswat.filescanner.service.impl;

import com.opswat.filescanner.config.OpswatConfigurations;
import com.opswat.filescanner.exceptions.GeneralApiException;
import com.opswat.filescanner.exceptions.JsonFormatException;
import com.opswat.filescanner.model.api.response.error.GeneralErrorResponse;
import com.opswat.filescanner.model.api.response.file.analysis.FileAnalysisResponse;
import com.opswat.filescanner.model.api.response.file.analysis.ScanEngine;
import com.opswat.filescanner.model.api.response.file.upload.FileUploadResponse;
import com.opswat.filescanner.service.FileScannerService;
import com.opswat.filescanner.utils.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Map;

@Slf4j
@Service("fileScannerService")
public class FileScannerServiceImpl implements FileScannerService {

    @Autowired
    RestApiClientService restApiClientService;

    @Autowired
    JsonParser jsonParser;

    @Autowired
    OpswatConfigurations opswatConfigurations;

    @Override
    public void scanFiles(String filePath){
        File filePathAsFile = new File(filePath);
        if(filePathAsFile.isDirectory()){
            for(File eachFile : filePathAsFile.listFiles()){
                scanFile(filePathAsFile+File.separator+eachFile.getName());
            }
        }else if(filePathAsFile.exists()){
            scanFile(filePath);
        }else{
            log.error("File:{} doesn't exist",filePath);
        }
    }

    /**
     * @param filePath
     * Scans file given in parameter with certain checks
     * if hash of file is has been analyzed, get and print the results
     * else post file and continue to see if progress is complete in 5 second intervals
     * once file process is done, print the results
     */
    private void scanFile(String filePath){
        try {
            String hash = HashUtils.hashFile(filePath, opswatConfigurations.hashAlgo);
            ResponseEntity<String> getHashResponseEntity = restApiClientService.getHashResult(hash);
            if (getHashResponseEntity.getStatusCode().is2xxSuccessful()) {
                FileAnalysisResponse fileAnalysisResponse = jsonParser.parseJson(getHashResponseEntity.getBody(), FileAnalysisResponse.class);
                if(StringUtils.hasText(fileAnalysisResponse.getProcessInfo().getBlockedReason())){
                    log.error("File was blocked during processing, {}",fileAnalysisResponse.getProcessInfo().getBlockedReason());
                }
                printAnalysis(filePath,fileAnalysisResponse);
            }else{
                ResponseEntity<String> postFileResponseEntity = restApiClientService.postFile(new File(filePath));
                if(postFileResponseEntity.getStatusCode().is2xxSuccessful()){
                    FileUploadResponse fileUploadResponse = jsonParser.parseJson(postFileResponseEntity.getBody(), FileUploadResponse.class);
                    ResponseEntity<String> getDataIdResponseEntity = restApiClientService.getFileResult(fileUploadResponse.getDataId());
                    if(!getDataIdResponseEntity.getStatusCode().is2xxSuccessful()) {
                        throw new GeneralApiException(getDataIdResponseEntity.getBody());
                    }
                    FileAnalysisResponse fileAnalysisResponse = jsonParser.parseJson(getDataIdResponseEntity.getBody(), FileAnalysisResponse.class);
                    if(StringUtils.hasText(fileAnalysisResponse.getProcessInfo().getBlockedReason())){
                        log.error("File was blocked during processing, {}",fileAnalysisResponse.getProcessInfo().getBlockedReason());
                    }
                    while(fileAnalysisResponse.getProcessInfo().getProgressPercentage()<100){
                        log.info("File is not done processing, waiting 5 seconds to try again");
                        Thread.sleep(5000l);
                        getDataIdResponseEntity = restApiClientService.getFileResult(fileUploadResponse.getDataId());
                        if(!getDataIdResponseEntity.getStatusCode().is2xxSuccessful()) {
                            throw new GeneralApiException(getDataIdResponseEntity.getBody());
                        }
                        fileAnalysisResponse = jsonParser.parseJson(getDataIdResponseEntity.getBody(), FileAnalysisResponse.class);
                    }
                    printAnalysis(filePath,fileAnalysisResponse);
                }else if(postFileResponseEntity.getStatusCode().is4xxClientError()){
                    throw new GeneralApiException(postFileResponseEntity.getBody());
                }
            }
        }catch(GeneralApiException e){
            try{
                GeneralErrorResponse generalErrorResponse = jsonParser.parseJson(e.getJson(),GeneralErrorResponse.class);
                log.error("API failed with ={}",generalErrorResponse.getError().getMessages());
            }catch (JsonFormatException ex){
                log.error("Unable to deserialize general error response");
            }
        }catch(Exception e){
            log.error("error ={}", e.getMessage());
        }
    }

    private void printAnalysis(String fileName, FileAnalysisResponse fileAnalysisResponse){
        log.info("filename:{}",fileName);
        log.info("overall_status:{}",fileAnalysisResponse.getScanResults().getScanAllResultA());
        for(Map.Entry<String, ScanEngine> engine : fileAnalysisResponse.getScanResults().getScanDetails().entrySet()){
            log.info("engine:{}",engine.getKey());
            log.info("threat_found:{}",engine.getValue().getThreatFound());
            log.info("scan_result:{}",engine.getValue().getScanResult());
            log.info("def_time:{}",engine.getValue().getDefTime());
        }
    }
}
