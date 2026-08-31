package com.apigatewayservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ApigatewayServiceApplication {

    public static void main(String[] args) {
        log.info("Starting API Gateway...");
        SpringApplication.run(ApigatewayServiceApplication.class, args);
        log.info("API Gateway started successfully");
    }

}
