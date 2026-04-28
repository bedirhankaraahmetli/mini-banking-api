package com.banking.garantiintegrationservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class LoanResponseDto {
    private LoanData data; // Done according to the JSON response from Garanti API
    private LoanResult result; // Done according to the JSON response from Garanti API

    // --- Nested Classes ---

    @Data
    public static class LoanData {
        private List<InstallmentDetail> installments;
        private LoanTotal total;
    }

    @Data
    public static class InstallmentDetail {
        private Integer installmentNumber;
        private String installmentDate;
        private BigDecimal installmentAmount;
        private BigDecimal capitalAmount;
        private BigDecimal interestAmount;
        private BigDecimal fundAmount;
        private BigDecimal taxamount;
        private BigDecimal remainingCapitalAmount;
    }

    @Data
    public static class LoanTotal {
        private Integer dueNum;
        private BigDecimal installmentAmount;
        private BigDecimal capitalAmount;
        private BigDecimal totalInstallmentAmount;
        private BigDecimal annualCostRate;
        private BigDecimal interestAmount;
        private BigDecimal expenseAmount;
    }

    @Data
    public static class LoanResult {
        private Integer code;
        private String info;
    }

}
