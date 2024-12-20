package com.fydhfzh.order_service.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fydhfzh.order_service.dto.ApiResponse;
import com.fydhfzh.order_service.dto.order.OrderRequest;
import com.fydhfzh.order_service.dto.order.OrderResponse;
import com.fydhfzh.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> all(Principal principal) {
        String email = principal.getName();
        List<OrderResponse> orderResponses = orderService.findAll(email);

        ApiResponse response = new ApiResponse("success", "Orders retrieved successfully", 200, orderResponses);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(Principal principal, @RequestBody OrderRequest orderPayload) {
        String email = principal.getName();
        orderPayload.setUserEmail(email);

        OrderResponse orderResponse = orderService.save(orderPayload);

        ApiResponse response = new ApiResponse("success", "Order created successfully", 200, orderResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> one(Principal principal, @PathVariable int id) {
        String email = principal.getName();
        OrderResponse orderResponse = orderService.findById(email, id);

        ApiResponse response = new ApiResponse("success", "Order retrieved successfully", 200, orderResponse);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(Principal principal, @PathVariable int id) {
        String email = principal.getName();
        orderService.delete(email, id);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(200)
                .message("Order deleted successfully")
                .build();

        return ResponseEntity.ok(response);
    }
}
