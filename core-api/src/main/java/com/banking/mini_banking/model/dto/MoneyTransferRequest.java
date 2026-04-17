package com.banking.mini_banking.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyTransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;
}

// DTO (Data Transfer Object) class used for transferring money between
// accounts. It contains the necessary fields for the money transfer process,
// such as the source account number, destination account number, and the amount
// to be transferred. This class is used to transfer data from the client to the
// server when a money transfer request is made.
