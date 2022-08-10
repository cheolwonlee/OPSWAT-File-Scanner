package com.opswat.filescanner.service.impl;

import com.opswat.filescanner.config.CommonConstants;
import com.opswat.filescanner.config.CommonUriPath;
import com.opswat.filescanner.config.OpswatConfigurations;
import com.opswat.filescanner.exceptions.ApiException;
import com.opswat.filescanner.service.RestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestApiClientService {

    @Autowired
    private RestApiClient restApiClient;

    @Autowired
    private OpswatConfigurations opswatConfigurations;


    /**
     * @param hash
     * @return ResponseEntity<String>
     * @throws ApiException
     * calls GET /hash/{hash} API to get results of a hash value of a file
     */
    public ResponseEntity<String> getHashResult(String hash) throws ApiException {
        try{
            URI uri = new URI(opswatConfigurations.baseUrl+ CommonUriPath.HASH(hash));
            return restApiClient.getScanReports(uri,getHeaderMap(opswatConfigurations.apiKey));
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }
    }

    /**
     * @param dataId
     * @return ResponseEntity<String>
     * @throws ApiException
     * calls GET /file/{dataId} API to get results of a dataId value of a file
     */
    public ResponseEntity<String> getFileResult(String dataId) throws ApiException{
        try{
            URI uri = new URI(opswatConfigurations.baseUrl+ CommonUriPath.FILE(dataId));
            return restApiClient.getScanReports(uri,getHeaderMap(opswatConfigurations.apiKey));
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }
    }


    /**
     * @param file
     * @return ResponseEntity<String>
     * @throws ApiException
     * calls POST /file API to upload file to sandbox
     */
    public ResponseEntity<String> postFile(File file) throws ApiException{
        try{
            URI uri = new URI(opswatConfigurations.baseUrl+ CommonUriPath.FILE);
            return restApiClient.postAnalyzeFile(uri,getHeaderMap(opswatConfigurations.apiKey),file);
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }
    }

    private Map<String, String> getHeaderMap(String apiKey) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(CommonConstants.HEADER_API_KEY, apiKey);
        return headerMap;
    }
}
