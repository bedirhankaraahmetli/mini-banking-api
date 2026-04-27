package com.banking.garantiintegrationservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.banking.garantiintegrationservice.dto.LoanRequestDto;
import com.banking.garantiintegrationservice.dto.LoanResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GarantiLoanService {

    private final RestClient garantiRestClient; // Inject the RestClient bean configured for Garanti API
    private final GarantiAuthService authService; // Inject the AuthService to get access tokens

    public LoanResponseDto calculateLoan(LoanRequestDto requestDto) {
        // 1. Get an fresh OAuth 2.0 access token using the AuthService
        String accessToken = authService.getAccessToken();

        System.out.println("Making API call to Garanti to calculate loan");
        System.out.println("Requested Amount: " + requestDto.getLoanAmount() + "TL, Installment Amount:"
                + requestDto.getDueNum() + "months");

        // 2. Use the token to call the Garanti API for loan calculation
        return garantiRestClient.post()
                .uri("/loans/v1/paymentPlan")
                .header("Authorization", "Bearer " + accessToken) // Set the Bearer token in the Authorization header
                .body(requestDto) // Send the LoanRequestDto as the request body
                .retrieve()
                .body(LoanResponseDto.class); // Return the response as a LoanResponseDto object
    }
}
