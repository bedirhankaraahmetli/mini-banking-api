package com.banking.garantiintegrationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.garantiintegrationservice.dto.LoanRequestDto;
import com.banking.garantiintegrationservice.dto.LoanResponseDto;
import com.banking.garantiintegrationservice.service.GarantiLoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/integration/loans")
@RequiredArgsConstructor
public class LoanController {

    private final GarantiLoanService loanService;

    @PostMapping("/calculate")
    public ResponseEntity<LoanResponseDto> calculatePaymentPlan(@RequestBody LoanRequestDto requestDto) {

        requestDto.setLoanType("2");
        requestDto.setCampaignCode("TESTFIRM");

        LoanResponseDto response = loanService.calculateLoan(requestDto);
        return ResponseEntity.ok(response); // Return the loan calculation result in the response body
    }
}
