package com.banking.garantiintegrationservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDto {
    private String loanType; // Required: "2" (General Purpose)
    private String campaignCode; // Required "TESTFIRM"
    private BigDecimal loanAmount; // Required: Loan amount (e.g., 10000.00)
    private Integer dueNum; // Required: Loan term in months (e.g., 36)
}

// This DTO represents the request body for creating a loan application. It
// includes fields for the loan type, campaign code, loan amount, and loan term.