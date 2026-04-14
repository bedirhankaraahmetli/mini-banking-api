package com.banking.mini_banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.mini_banking.model.dto.LoginRequest;
import com.banking.mini_banking.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and login.")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Authenticate a user and return a JWT token.")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}

// The AuthController class is responsible for handling authentication-related
// HTTP requests in the mini banking application. It defines an endpoint for
// user login, which accepts a LoginRequest containing the user's email and
// password. The controller uses the AuthService to authenticate the user and
// generate a JWT token if the authentication is successful. The generated token
// is then returned in the response, allowing the user to access protected
// resources in the application. The controller is annotated with Swagger
// annotations to provide API documentation for the authentication endpoint.
