package com.eurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServerApplication {
    public static void main(String[] args) {
        log.info("Starting Eureka Server application");
        SpringApplication.run(EurekaServerApplication.class, args);
        log.info("Eureka Server application started successfully");
    }

}
