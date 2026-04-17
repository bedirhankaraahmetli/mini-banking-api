package com.banking.mini_banking.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerResponse {
    private Long id;
    private String identityNumber;
    private String fullName;
    private String email;
    private List<AccountResponse> accounts;
}

// DTO (Data Transfer Object) class used for returning customer information in
// API responses. It contains fields such as customer ID, identity number, full
// name, email, and a list of accounts associated with the customer. This class
// is used to transfer customer data from the server to the client when
// retrieving customer details or listing customers.