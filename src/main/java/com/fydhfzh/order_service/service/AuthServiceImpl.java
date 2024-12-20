package com.fydhfzh.order_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fydhfzh.order_service.dto.auth.AuthRequest;
import com.fydhfzh.order_service.dto.auth.AuthResponse;
import com.fydhfzh.order_service.dto.auth.RegisterRequest;
import com.fydhfzh.order_service.entity.Role;
import com.fydhfzh.order_service.entity.User;
import com.fydhfzh.order_service.repository.UserRepository;
import com.fydhfzh.order_service.security.JwtTokenProvider;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

        private final UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        private final JwtTokenProvider jwtTokenProvider;

        private final AuthenticationManager authenticationManager;

        @Transactional
        @Override
        public AuthResponse register(RegisterRequest payload) {
                List<Role> roles = new ArrayList<>();

                User user = User.builder()
                                .firstName(payload.getFirstName())
                                .lastName(payload.getLastName())
                                .email(payload.getEmail())
                                .password(passwordEncoder.encode(payload.getPassword()))
                                .build();

                roles.add(
                                Role.builder()
                                                .role("USER")
                                                .user(user)
                                                .build());

                user.setRoles(roles);

                User newUser = userRepository.save(user);

                System.out.println(newUser);

                String jwtToken = jwtTokenProvider.generateToken(newUser);

                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        @Transactional
        @Override
        public AuthResponse authenticate(AuthRequest payload) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                payload.getEmail(),
                                                payload.getPassword()));

                User user = userRepository.findByEmailActive(payload.getEmail()).orElseThrow(
                                () -> new NoSuchElementException(
                                                "No user present with email: " + payload.getEmail()));

                System.out.println(user);

                String jwtToken = jwtTokenProvider.generateToken(user);

                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

}
