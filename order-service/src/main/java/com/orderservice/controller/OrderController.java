package com.orderservice.controller;


import com.orderservice.dto.request.OrderRequest;
import com.orderservice.dto.response.OrderResponse;
import com.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private static final Logger log =
            LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(
            @RequestBody OrderRequest request) {
        log.info("Create order request received");
        Object response = orderService.createOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(
            @PathVariable Long id) {
        log.info("Get order request");
        Object response=orderService.getOrderById(id);
        return ResponseEntity.ok(
                response
        );
    }
}
