package com.banking.mini_banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.mini_banking.model.dto.loan.LoanCalculationRequest;
import com.banking.mini_banking.model.dto.loan.LoanCalculationresponse;
import com.banking.mini_banking.service.GarantiIntegrationClient;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final GarantiIntegrationClient integrationClient;

    @PostMapping("/calculate")
    public ResponseEntity<LoanCalculationresponse> calculateLoan(@RequestBody LoanCalculationRequest request) {
        LoanCalculationresponse response = integrationClient.getLoanPlan(request);
        return ResponseEntity.ok(response);
    }
}
