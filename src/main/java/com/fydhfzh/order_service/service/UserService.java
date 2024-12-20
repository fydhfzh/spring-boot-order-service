package com.fydhfzh.order_service.service;

import java.util.List;

import com.fydhfzh.order_service.dto.user.UserResponse;

public interface UserService {
    public UserResponse findByEmail(String email);

    public List<UserResponse> findAll();
}
