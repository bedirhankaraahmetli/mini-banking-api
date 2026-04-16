package com.banking.mini_banking.service;

import com.banking.mini_banking.model.dto.MoneyTransferRequest;
import com.banking.mini_banking.model.entity.Account;
import com.banking.mini_banking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enabling Mockito annotations
public class AccountServiceTest {
    @Mock // Mocking the AccountRepository
    private AccountRepository accountRepository;

    @InjectMocks // Injecting the mocked repository into AccountService
    private AccountService accountService;

    @Test
    void shouldTransferMoneySuccessfully() {
        // 1. Arrange
        Account fromAccount = new Account();
        fromAccount.setAccountNumber("SENDER");
        fromAccount.setBalance(new BigDecimal("5000"));

        Account toAccount = new Account();
        toAccount.setAccountNumber("RECEIVER");
        toAccount.setBalance(new BigDecimal("1000"));

        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setFromAccountNumber("SENDER");
        request.setToAccountNumber("RECEIVER");
        request.setAmount(new BigDecimal("2000"));

        // Telling Mockito what to return when the repository methods are called
        when(accountRepository.findByAccountNumber("SENDER")).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber("RECEIVER")).thenReturn(Optional.of(toAccount));

        // 2. Act
        accountService.transferMoney(request);

        // 3. Assert
        // Does the sender's balance decrease by 2000?
        assertEquals(new BigDecimal("3000"), fromAccount.getBalance());
        // Does the receiver's balance increase by 2000?
        assertEquals(new BigDecimal("3000"), toAccount.getBalance());

        // Confirmation: Are the save methods called for both accounts?
        verify(accountRepository, times(1)).save(fromAccount);
        verify(accountRepository, times(1)).save(toAccount);
    }

    @Test
    void shouldThrowExceptionWhenInsufficientFunds() {
        // 1. Arrange
        Account fromAccount = new Account();
        fromAccount.setAccountNumber("SENDER");
        fromAccount.setBalance(new BigDecimal("1000"));

        Account toAccount = new Account();
        toAccount.setAccountNumber("RECEIVER");
        toAccount.setBalance(new BigDecimal("1000"));

        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setFromAccountNumber("SENDER");
        request.setToAccountNumber("RECEIVER");
        request.setAmount(new BigDecimal("2000"));

        when(accountRepository.findByAccountNumber("SENDER")).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber("RECEIVER")).thenReturn(Optional.of(toAccount));

        // 2. Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.transferMoney(request);
        });

        // Is the exception message correct?
        assertEquals("Insufficient balance in the source account.", exception.getMessage());

        // Confirmation: Are the save methods NOT called for either account?
        verify(accountRepository, never()).save(any());
    }
}
