package com.banking.mini_banking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}

// The LoginRequest class is a Data Transfer Object (DTO) that represents the
// data required for a user to log in to the mini banking application. It
// contains two fields: `email` and `password`, which are the credentials that
// the user will provide when attempting to authenticate. The class is annotated
// with Lombok's `@Getter` and `@Setter` annotations, which automatically
// generate getter and setter methods for the fields, allowing for easy access
// and modification of the data. This DTO will be used in the authentication
// process, where the user's email and password will be validated against the
// stored credentials in the database.
