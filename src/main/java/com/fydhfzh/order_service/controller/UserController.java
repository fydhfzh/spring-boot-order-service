package com.fydhfzh.order_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fydhfzh.order_service.dto.ApiResponse;
import com.fydhfzh.order_service.dto.user.UserResponse;
import com.fydhfzh.order_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String authenticatedUser() {
        // todo
        return null;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> allUsers() {
        List<UserResponse> userResponse = userService.findAll();

        ApiResponse response = new ApiResponse("success",
                "Users retrieved successfully", 200, userResponse);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
