package com.banking.mini_banking.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.banking.mini_banking.model.dto.loan.LoanCalculationRequest;
import com.banking.mini_banking.model.dto.loan.LoanCalculationresponse;

@Service
public class GarantiIntegrationClient {

    private final RestClient restClient;

    public GarantiIntegrationClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public LoanCalculationresponse getLoanPlan(LoanCalculationRequest request) {

        Map<String, Object> integrationRequest = Map.of(
                "loanAmount", request.getAmount(),
                "dueNum", request.getMonths());

        Map response = restClient.post()
                .uri("/api/v1/integration/loans/calculate")
                .body(integrationRequest)
                .retrieve()
                .body(Map.class);

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Map<String, Object> total = (Map<String, Object>) data.get("total");

        // Building clean DTO to send Frontend
        return LoanCalculationresponse.builder()
                .monthlyInstallment(new BigDecimal(total.get("installmentAmount").toString()))
                .totalPayment(new java.math.BigDecimal(total.get("totalInstallmentAmount").toString()))
                .totalInterest(new java.math.BigDecimal(total.get("interestAmount").toString()))
                .annualCostRate(new java.math.BigDecimal(total.get("annualCostRate").toString()))
                .build();
    }
}
