package com.fydhfzh.order_service.exception.order;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fydhfzh.order_service.exception.ErrorMessage;

@ControllerAdvice
public class OrderControllerExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(OrderNotFoundException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status("error")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
