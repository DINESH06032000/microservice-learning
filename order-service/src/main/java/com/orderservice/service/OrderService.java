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

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "createOrderFallback")
    @RateLimiter(name = "USER-SERVICE")
    @Retry(name = "USER-SERVICE")
    public Object createOrder(OrderRequest request) {

        Long userId = request.getUserId();

        UserResponse user = userClient.getUserById(userId);

        OrderEntity order = new OrderEntity();
        order.setUserId(userId);

        OrderEntity savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getUserId(),
                user
        );
    }


    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "getOrderFallback")
    @RateLimiter(name = "USER-SERVICE")
    @Retry(name = "USER-SERVICE")
    public Object getOrderById(Long orderId) {

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found: " + orderId
                        )
                );

        UserResponse user =
                userClient.getUserById(order.getUserId());

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                user
        );
    }


    // Fallback for createOrder
    public Object createOrderFallback(
            OrderRequest request,
            Throwable throwable) {

       return "Circuit Breaker fallback: User Service is unavailable";
    }


    // Fallback for getOrderById
    public Object getOrderFallback(
            Long orderId,
            Throwable throwable) {

        return "Circuit Breaker fallback: User Service is unavailable";
    }
}
