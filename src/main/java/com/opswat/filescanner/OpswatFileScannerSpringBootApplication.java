package com.opswat.filescanner;

import com.opswat.filescanner.service.FileScannerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@Slf4j
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class OpswatFileScannerSpringBootApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(OpswatFileScannerSpringBootApplication.class)
                .run(args);
    }
    @Override
    public void run(String... args) throws Exception {
        FileScannerService fileScannerService = (FileScannerService) applicationContext.getBean("fileScannerService");
        fileScannerService.scanFile();
        initiateShutDown();
    }

    private void initiateShutDown() {
        SpringApplication.exit(applicationContext, () -> 0);
    }
}
