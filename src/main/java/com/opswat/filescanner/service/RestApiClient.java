package com.opswat.filescanner.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "restApiClient", url = "https://google.com")
public interface RestApiClient {

    @GetMapping(produces = "application/json")
    ResponseEntity<String> getScanReports(URI baseUrl, @RequestHeader Map<String, String> headerMap);

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    ResponseEntity<String> postAnalyzeFile(URI baseUrl, @RequestHeader Map<String, String> headerMap, @RequestBody Object request);

}
