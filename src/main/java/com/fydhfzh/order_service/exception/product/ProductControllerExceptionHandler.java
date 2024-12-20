package com.fydhfzh.order_service.exception.product;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fydhfzh.order_service.exception.ErrorMessage;

@ControllerAdvice
public class ProductControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status("error")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorMessage> insufficientStockException(InsufficientStockException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status("error")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
