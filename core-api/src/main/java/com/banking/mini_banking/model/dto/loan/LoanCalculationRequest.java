package com.banking.mini_banking.model.dto.loan;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanCalculationRequest {
    private BigDecimal amount;
    private Integer months;
}
