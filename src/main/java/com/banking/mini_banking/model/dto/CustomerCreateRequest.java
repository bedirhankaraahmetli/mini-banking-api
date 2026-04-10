package com.banking.mini_banking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateRequest {
    private String identityNumber;
    private String fullName;
    private String email;
    private String password;
}

// DTO (Data Transfer Object) class used for creating a new customer. It
// contains the necessary fields for the customer creation process, such as
// identity number, full name, email, and password. This class is used to
// transfer data from the client to the server when a new customer is being
// created.