package com.fydhfzh.order_service.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fydhfzh.order_service.dto.user.UserResponse;
import com.fydhfzh.order_service.entity.User;
import com.fydhfzh.order_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;

        @Override
        public List<UserResponse> findAll() {
                List<User> users = userRepository.findAllActive();

                return users.stream().map(
                                user -> UserResponse.builder()
                                                .id(user.getId())
                                                .firstName(user.getFirstName())
                                                .lastName(user.getLastName())
                                                .email(user.getEmail())
                                                .phone(user.getPhone())
                                                .createdAt(user.getCreatedAt())
                                                .updatedAt(user.getUpdatedAt())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public UserResponse findByEmail(String email) {
                User user = userRepository.findByEmailActive(email).orElseThrow(
                                () -> new NoSuchElementException("No user present with email: " + email));

                UserResponse userResponse = UserResponse.builder()
                                .id(user.getId())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .phone(user.getPhone())
                                .createdAt(user.getCreatedAt())
                                .updatedAt(user.getUpdatedAt())
                                .build();

                return userResponse;
        }

}
