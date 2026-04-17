package com.banking.mini_banking.controller;

import com.banking.mini_banking.model.dto.AccountCreateRequest;
import com.banking.mini_banking.model.dto.MoneyTransferRequest;
import com.banking.mini_banking.model.entity.Account;
import com.banking.mini_banking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts") // Base URL for all account-related endpoints. For example, to create a new
                                    // account, the endpoint would be /api/v1/accounts.
@RequiredArgsConstructor
@Tag(name = "Account Controller", description = "Endpoints for managing accounts in the mini banking application.")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Create a new account", description = "Creates a new account for an existing customer in the mini banking application.")
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreateRequest request) {

        Account createdAccount = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PostMapping("/transfer")
    @Operation(summary = "Transfer money between accounts", description = "Transfers money from one account to another in the mini banking application.")
    public ResponseEntity<String> transferMoney(@RequestBody MoneyTransferRequest request) {
        accountService.transferMoney(request);
        return ResponseEntity.ok("Money transferred successfully.");
    }

    @GetMapping("/{accountNumber}")
    @Operation(summary = "Get account details", description = "Retrieves the details of an account based on the provided account number.")
    public ResponseEntity<Account> getAccountDetails(@PathVariable String accountNumber) { // PathVariable is used to
                                                                                           // extract the account number
                                                                                           // from the URL path.
        Account account = accountService.getAccountDetails(accountNumber);
        return ResponseEntity.ok(account);
    }
}

// This class is a REST controller in the Spring framework that handles HTTP
// requests related to accounts in the mini banking application. It defines
// endpoints for creating new accounts and transferring money between accounts.
// The controller uses the AccountService to perform business logic operations,
// such as creating accounts and processing money transfers. The endpoints are
// documented with Swagger annotations for better API documentation. The
// createAccount method accepts a JSON request body that is deserialized into an
// AccountCreateRequest object, while the transferMoney method accepts a
// MoneyTransferRequest object for processing money transfers. Both methods
// return appropriate HTTP responses based on the outcome of the operations.
