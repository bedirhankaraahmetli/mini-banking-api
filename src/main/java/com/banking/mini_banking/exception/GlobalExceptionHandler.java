package com.banking.mini_banking.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Our business logic exceptions (e.g., "Insufficient funds", "Account not
    // found", etc.)
    @ExceptionHandler(RuntimeException.class)
    // This is a catch-all for any RuntimeException thrown in the application. In a
    // real app, you might want to create custom exception classes (e.g.,
    // InsufficientFundsException) and handle them separately for more specific
    // error messages and status codes.
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Rather than a 500 Internal Server Error, we can return a 400 Bad Request for
        // business logic errors
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // 2. Handle validation errors (e.g., Missing/invalid fields in the request
    // body)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // This handles exceptions thrown when @Valid fails on a request body. It
    // extracts the validation errors and returns them in a structured format.
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Loop through all validation errors and put them in the map with field name as
        // key and error message as value
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // Get the name of the field that caused the error
            String errorMessage = error.getDefaultMessage(); // Get the default error message (e.g., "must not be null",
                                                             // "size must be between 1 and 100", etc.)
            errors.put(fieldName, errorMessage); // Add the field name and error message to the map
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors); // Return the map of field errors with a 400
                                                                           // Bad Request status
    }
}

// This GlobalExceptionHandler class is annotated with @RestControllerAdvice,
// which allows it to handle exceptions thrown by any controller in the
// application. It has two methods:
// 1. handleRuntimeException() to catch any RuntimeException and return a 400
// Bad Request with the exception message, and
// 2. handleMethodArgumentNotValidException() to catch validation errors when
// @Valid fails on a request body, extract the field errors, and return them in
// a structured format with a 400 Bad Request status.
