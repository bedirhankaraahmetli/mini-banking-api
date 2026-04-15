package com.banking.mini_banking.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateRequest {

    @NotBlank(message = "Identity number is required")
    @Size(min = 11, max = 11, message = "Identity number must be exactly 11 characters")
    private String identityNumber;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}

// DTO (Data Transfer Object) class used for creating a new customer. It
// contains the necessary fields for the customer creation process, such as
// identity number, full name, email, and password. This class is used to
// transfer data from the client to the server when a new customer is being
// created.