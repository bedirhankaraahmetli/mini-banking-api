package com.banking.mini_banking.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.mini_banking.model.dto.LoginRequest;
import com.banking.mini_banking.model.entity.Customer;
import com.banking.mini_banking.repository.CustomerRepository;
import com.banking.mini_banking.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(LoginRequest request) {
        // 1. Find the customer by email
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password."));

        // 2. Check if the password matches
        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }

        // 3. If authentication is successful, generate a JWT token
        return jwtService.generateToken(customer.getEmail());
    }
}

// The AuthService class is responsible for handling the authentication logic of
// the mini banking application. It contains a method `login` that takes a
// `LoginRequest` DTO as input, which contains the user's email and password.
// The method performs the following steps:
// 1. It uses the `CustomerRepository` to find a customer by their email. If the
// customer is not found, it throws a runtime exception indicating invalid
// credentials.
// 2. If the customer is found, it checks if the provided password matches the
// stored password using the `PasswordEncoder`. If the passwords do not match,
// it throws a runtime exception indicating invalid credentials.
