package com.banking.mini_banking.model.dto.loan;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanCalculationresponse {
    private BigDecimal monthlyInstallment;
    private BigDecimal totalPayment;
    private BigDecimal totalInterest;
    private BigDecimal annualCostRate;
}
