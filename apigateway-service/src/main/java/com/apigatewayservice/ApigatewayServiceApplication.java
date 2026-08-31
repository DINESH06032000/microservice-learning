package com.apigatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApigatewayServiceApplication {
    private static final Logger log =
            LoggerFactory.getLogger(ApigatewayServiceApplication.class);
    public static void main(String[] args) {

        log.info("Starting API Gateway...");
        SpringApplication.run(ApigatewayServiceApplication.class, args);
        log.info("API Gateway started successfully");
    }

}
