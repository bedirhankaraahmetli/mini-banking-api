package com.banking.mini_banking.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateRequest {
    private Long customerId;
    private String currency;
    private BigDecimal initialBalance;
}

// DTO (Data Transfer Object) class used for creating a new account. It contains
// the necessary fields for the account creation process, such as the
// customer ID to which the account will be linked, the currency of the account,
// and the initial balance. This class is used to transfer data from the client
// to the server when a new account is being created.
