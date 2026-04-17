package com.banking.mini_banking.model.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountResponse {
    private String accountNumber;
    private BigDecimal balance;
    private String currency;
}

// DTO (Data Transfer Object) class used for returning account information in
// API responses. It contains fields such as account number, balance, and
// currency. This class is used to transfer account data from the server to the
// client when retrieving account details or listing accounts for a customer.