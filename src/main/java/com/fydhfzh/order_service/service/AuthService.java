package com.fydhfzh.order_service.service;

import com.fydhfzh.order_service.dto.auth.AuthRequest;
import com.fydhfzh.order_service.dto.auth.AuthResponse;
import com.fydhfzh.order_service.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest payload);

    AuthResponse authenticate(AuthRequest payload);
}
