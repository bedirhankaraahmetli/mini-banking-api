package com.banking.mini_banking.service;

import com.banking.mini_banking.grpc.NotificationRequest;
import com.banking.mini_banking.grpc.NotificationResponse;
import com.banking.mini_banking.grpc.NotificationServiceGrpc.NotificationServiceBlockingStub;
import com.banking.mini_banking.model.dto.AccountCreateRequest;
import com.banking.mini_banking.model.dto.MoneyTransferRequest;
import com.banking.mini_banking.model.entity.Account;
import com.banking.mini_banking.model.entity.Customer;
import com.banking.mini_banking.repository.AccountRepository;
import com.banking.mini_banking.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @GrpcClient("notification-service")
    // This annotation injects a gRPC client for the notification service. The
    // "notification-service" string should match the name of the gRPC service
    // defined in your application configuration.
    private NotificationServiceBlockingStub notificationStub;
    // This is the gRPC client stub that will be used to send notifications to the
    // notification service. The stub provides methods to call the gRPC service's
    // endpoints.

    public Account createAccount(AccountCreateRequest request) {
        // Implementation for creating a new account based on the provided request.
        // This method will include logic to validate the request, check if the
        // customer exists, generate a unique account number, and save the new
        // account to the database using the accountRepository.

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + request.getCustomerId()));

        Account account = Account.builder()
                .customer(customer) // Set the customer for the account based on the provided customer ID.
                .accountNumber(generateAccountNumber()) // Generate a unique account number for the new account.
                .currency(request.getCurrency()) // Set the currency for the account based on the provided request data.
                .balance(request.getInitialBalance() != null ? request.getInitialBalance() : BigDecimal.ZERO)
                // Set the initial balance for the account. If the request does not provide an
                // initial balance, default to zero.
                .build(); // Use the builder pattern to create a new Account instance with the specified
                          // properties.

        return accountRepository.save(account);
    }

    @Transactional // This annotation ensures that the entire money transfer process is atomic. If
                   // any part of the process fails (e.g., insufficient balance, account not
                   // found), the transaction will be rolled back to maintain data integrity.
    public void transferMoney(MoneyTransferRequest request) {
        // 1. Find the source and destination accounts using the accountRepository.
        Account fromAccount = accountRepository.findByAccountNumber(request.getFromAccountNumber())
                .orElseThrow(() -> new RuntimeException(
                        "Source account not found with account number: " + request.getFromAccountNumber()));

        Account toAccount = accountRepository.findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() -> new RuntimeException(
                        "Destination account not found with account number: " + request.getToAccountNumber()));

        // 2. Validate that the source account has sufficient balance for the transfer.
        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance in the source account.");
        }

        // 3. Perform the money transfer by deducting the amount from the source account
        // and adding it to the destination account.
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        // 4. Save the updated account information back to the database using the
        // accountRepository.
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // 5. MICRO-SERVICE COMMUNICATION: After successfully transferring the money, we
        // need to send a notification to the user. We will use gRPC to communicate with
        // the notification service.
        sendGrpcNotification(request.getFromAccountNumber(), request.getAmount());
    }

    // Simple method to generate a unique account number. In a real application, you
    // might want to implement a more robust solution to ensure uniqueness and
    // follow specific formatting rules.
    private String generateAccountNumber() {
        return "TR" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10).toUpperCase();
        // This generates a random account number starting with "TR" followed by 10
        // characters derived from a UUID. The UUID is stripped of dashes and truncated
        // to ensure it fits the desired length. This is a simple approach and may not
        // be suitable for production use, where you would want to ensure that account
        // numbers are unique and follow specific business rules.
    }

    public Account getAccountDetails(String accountNumber) {
        // Implementation for retrieving account details based on the provided account
        // number. This method will use the accountRepository to find the account and
        // return its details. If the account is not found, it will throw an exception.

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found with account number: " + accountNumber));
    }

    // For readability and maintainability, we seperate the gRPC notification logic
    // into its own method. This method will be responsible for constructing the
    // gRPC request and sending it to the notification service using the injected
    // gRPC client stub. This separation allows us to keep the business logic of
    // account management clean and focused, while delegating the responsibility of
    // sending notifications to a dedicated method. This also makes it easier to
    // modify the notification logic in the future without affecting the core
    // account management functionality.
    private void sendGrpcNotification(String accountNumber, BigDecimal amount) {
        try {
            // Construct the packet according to the Proto file format.
            NotificationRequest grpcRequest = NotificationRequest.newBuilder()
                    .setAccountNumber(accountNumber) // Set the account number in the gRPC request.
                    .setAmount(amount.doubleValue()) // Set the amount in the gRPC request, converting it to a string.
                    .setMessage("Transaction was completed successfully")
                    .build(); // Build the gRPC request object.

            // Send the gRPC request to the notification service and receive the response.
            NotificationResponse grpcResponse = notificationStub.sendTransferNotification(grpcRequest);

            System.out.println("gRPC Notification Response: " + grpcResponse.getResultMessage());
        } catch (Exception e) {
            System.err.println("Error occurred while sending gRPC notification: " + e.getMessage());
        }
    }
}

// This class is a service component in the mini banking application that
// handles business logic related to accounts. It includes methods for creating
// new accounts and transferring money between accounts. The createAccount
// method takes an AccountCreateRequest DTO as input, validates the request, and
// creates a new account associated with a customer. The transferMoney method
// takes a MoneyTransferRequest DTO, validates the source account's balance, and
// performs the transfer by updating the balances of both the source and
// destination accounts. The service uses repositories to interact with the
// database and is annotated with @Service to indicate that it's a Spring
// service component. The @RequiredArgsConstructor annotation from Lombok is
// used to automatically generate a constructor for the final fields, allowing
// for dependency injection of the repositories. The @Transactional annotation
// on the transferMoney method ensures that the entire money transfer process is
// atomic, meaning that if any part of the process fails, the entire transaction
// will be rolled back to maintain data integrity.