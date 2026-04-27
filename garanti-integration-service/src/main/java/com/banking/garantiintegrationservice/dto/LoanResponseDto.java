package com.banking.garantiintegrationservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class LoanResponseDto {
    private List<InstallmentDetail> unitList;
    private String returnMessage;
    private int returnCode;

    @Data
    public static class InstallmentDetail {
        private Integer installmentNumber; // Installment number (e.g., 1, 2, 3, ...)
        private BigDecimal installmentAmount; // Monthly installment amount (e.g., 300.00)
        private BigDecimal interestAmount; // Interest amount for the installment (e.g., 50.00)
        private BigDecimal taxAmount; // Tax amount for the installment (e.g., 20.00)
        private BigDecimal fundAmount; // Principal amount for the installment (e.g., 250.00)
        private BigDecimal remainingCapitalAmount; // Remaining capital after the installment (e.g., 9750.00)
        private String installmentDate; // Due date for the installment (e.g., "2024-07-01")
    }
}
