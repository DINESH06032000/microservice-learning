package com.orderservice.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(
            OrderNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> handleFeignNotFoundException(
            FeignException.NotFound ex) {
        log.warn("USER-SERVICE returned 404: {}", ex.contentUTF8());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(
            FeignException ex) {
        log.error("Feign communication failed. Status: {}, Message: {}",
                ex.status(), ex.getMessage(), ex);
        return new ResponseEntity<>("something went wrong...",HttpStatus.SERVICE_UNAVAILABLE);
    }



}
