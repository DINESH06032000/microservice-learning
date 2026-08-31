package com.configserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@Slf4j
public class ConfigServerApplication {

    public static void main(String[] args) {
        log.info("Starting Config Server...");
        SpringApplication.run(ConfigServerApplication.class, args);
        log.info("Config Server started successfully");
    }

}
