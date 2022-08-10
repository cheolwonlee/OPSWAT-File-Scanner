package com.opswat.filescanner.service.impl;

import com.opswat.filescanner.service.FileScannerService;
import com.opswat.filescanner.utils.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service("fileScannerService")
public class FileScannerServiceImpl implements FileScannerService {

    @Autowired
    RestApiClientService restApiClientService;

    @Override
    public void scanFile(){
        try {
            String filePath = "test.log";
            String hash = HashUtils.hashFile(filePath, "MD5");
            log.info("hash = {}",hash);
            ResponseEntity<String> hashResult = restApiClientService.getHashResult(hash);
            if(!hashResult.getStatusCode().is2xxSuccessful()){
                ResponseEntity<String> postFileResponseEntity = restApiClientService.postFile(new File(filePath));
                

            }
            log.info("response = {}",hashResult);
        }catch (Exception e) {
            log.error("error ={}", e.getMessage());
        }
    }

    private void parseAndOutputAnalysisReport(String json){

    }

}
