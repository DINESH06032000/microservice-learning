package com.orderservice.service;


import com.orderservice.client.UserClient;
import com.orderservice.dto.request.OrderRequest;
import com.orderservice.dto.response.OrderResponse;
import com.orderservice.dto.response.UserResponse;
import com.orderservice.entity.OrderEntity;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;

    public OrderResponse createOrder(OrderRequest request) {

        Long userid= request.getUserId();
        // Call User Service
        UserResponse user =
                userClient.getUserById(userid);

        // Create Order
        OrderEntity order = new OrderEntity();
        order.setUserId(request.getUserId());

        // Save to PostgreSQL
        OrderEntity savedOrder =
                orderRepository.save(order);

        // Return response
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getUserId(),
                user
        );
    }

    public OrderResponse getOrderById(Long orderId) {

        // Get order from PostgreSQL
        OrderEntity order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new OrderNotFoundException(
                                        "Order not found: " + orderId
                                )
                        );

        // Get user from User Service
        UserResponse user =
                userClient.getUserById(order.getUserId());

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                user
        );
    }
}
