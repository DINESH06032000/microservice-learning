package com.orderservice.service;


import com.orderservice.client.UserClient;
import com.orderservice.dto.request.OrderRequest;
import com.orderservice.dto.response.OrderResponse;
import com.orderservice.dto.response.UserResponse;
import com.orderservice.entity.OrderEntity;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;

    @CircuitBreaker(name="USER-SERVICE", fallbackMethod = "fallBackCircuit")
    @RateLimiter(name="USER-SERVICE")
    @Retry(name="USER-SERVICE")
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

    @CircuitBreaker(name="USER-SERVICE", fallbackMethod = "fallBackCircuit")
    @RateLimiter(name="USER-SERVICE")
    @Retry(name="USER-SERVICE")
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

    public String fallBackCircuit(Long userId, Throwable throwable) {
        return "Service failed";
    }
}
