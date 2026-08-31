package com.orderservice.controller;


import com.orderservice.dto.request.OrderRequest;
import com.orderservice.dto.response.OrderResponse;
import com.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private final Environment environment;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(
            @RequestBody OrderRequest request) {
        log.info("Create order request received");
        Object response = orderService.createOrder(request);
        log.info("Order created successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(
            @PathVariable Long id) {
        log.info("Get order request");
        Object response=orderService.getOrderById(id);
        log.info("Order retrieved successfully");
        return ResponseEntity.ok(
                response
        );
    }
}
