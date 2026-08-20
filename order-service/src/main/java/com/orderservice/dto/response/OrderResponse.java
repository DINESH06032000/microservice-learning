package com.orderservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private Long userId;
    private UserResponse user;
}
