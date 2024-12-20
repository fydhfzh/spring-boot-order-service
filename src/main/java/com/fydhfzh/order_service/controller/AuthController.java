package com.fydhfzh.order_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fydhfzh.order_service.dto.ApiResponse;
import com.fydhfzh.order_service.dto.auth.AuthRequest;
import com.fydhfzh.order_service.dto.auth.AuthResponse;
import com.fydhfzh.order_service.dto.auth.RegisterRequest;
import com.fydhfzh.order_service.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegisterRequest registerPayload) {
        AuthResponse authResponse = authService.register(registerPayload);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.CREATED.value())
                .message("User registered successfully")
                .data(authResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody @Valid AuthRequest authPayload) {
        System.out.println(authPayload);
        AuthResponse authResponse = authService.authenticate(authPayload);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.OK.value())
                .message("User authenticated successfully")
                .data(authResponse)
                .build();
        System.out.println(response);

        return ResponseEntity.ok(response);
    }
}
