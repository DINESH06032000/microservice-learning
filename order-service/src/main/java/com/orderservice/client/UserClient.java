package com.orderservice.client;

import com.orderservice.config.FeignConfig;
import com.orderservice.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "USER-SERVICE",
        configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    public UserResponse getUserById(
            @PathVariable("id") Long id
    );
}