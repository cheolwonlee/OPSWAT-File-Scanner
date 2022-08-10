package com.opswat.filescanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpswatConfigurations {

    @Value("${opswat.hash.algo}")
    public String hashAlgo;

    @Value("${opswat.api.url.base}")
    public String baseUrl;

    @Value("${opswat.api.key}")
    public String apiKey;
}
