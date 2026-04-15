package com.banking.mini_banking.mapper;

import com.banking.mini_banking.model.dto.AccountResponse;
import com.banking.mini_banking.model.dto.CustomerResponse;
import com.banking.mini_banking.model.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Collections; // Importing Collections to use Collections.emptyList() for handling null accounts
import java.util.stream.Collectors; // Importing Collectors to use stream operations for mapping accounts to AccountResponse DTOs

@Component
public class CustomerMapper {

    public CustomerResponse toResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        // Maps the Customer entity to a CustomerResponse DTO. It also maps the list of
        // accounts associated with the customer to a list of AccountResponse DTOs. If
        // the customer has no accounts, it returns an empty list for the accounts field
        // in the CustomerResponse.
        return CustomerResponse.builder()
                .id(customer.getId())
                .identityNumber(customer.getIdentityNumber())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .accounts(customer.getAccounts() != null ? customer.getAccounts().stream()
                        .map(account -> AccountResponse.builder()
                                .accountNumber(account.getAccountNumber())
                                .balance(account.getBalance())
                                .currency(account.getCurrency())
                                .build())
                        .collect(Collectors.toList()) // Maps each Account entity to an AccountResponse DTO and collects
                                                      // them into a list
                        : Collections.emptyList()) // If the customer has no accounts, return an empty list instead of
                                                   // null
                .build();
    }
}

// This CustomerMapper class is a Spring component that provides a method to
// convert a Customer entity into a CustomerResponse DTO. The toResponse()
// method takes a Customer object as input and maps its fields to the
// corresponding fields in the CustomerResponse object. It also handles the
// mapping of the customer's accounts to a list of AccountResponse objects. If
// the input customer is null, it returns null. This mapper helps to separate
// the internal representation of the Customer entity from the external
// representation used in API responses, allowing for better encapsulation and
// flexibility in the application's design.
