package com.eurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    private static final Logger log =
            LoggerFactory.getLogger(EurekaServerApplication.class);
    public static void main(String[] args) {
        log.info("Starting Eureka Server application");
        SpringApplication.run(EurekaServerApplication.class, args);
        log.info("Eureka Server application started successfully");
    }

}
