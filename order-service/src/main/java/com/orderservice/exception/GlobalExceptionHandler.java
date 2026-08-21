package com.orderservice.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(
            FeignException ex) {

        return new ResponseEntity<>("something went wrong...",HttpStatus.SERVICE_UNAVAILABLE);
    }



}
