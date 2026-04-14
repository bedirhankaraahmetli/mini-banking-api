package com.banking.mini_banking.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.mini_banking.model.dto.CustomerCreateRequest;
import com.banking.mini_banking.model.entity.Customer;
import com.banking.mini_banking.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service // Indicates that this class is a service component in the Spring framework. It
         // is responsible for containing business logic related to customers in the mini
         // banking application.
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments (final
                         // fields).
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer createCustomer(CustomerCreateRequest request) {
        // 1. Business Validations
        if (customerRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new RuntimeException("Customer with the same identity number already exists.");
        }
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Customer with the same email already exists.");
        }

        // 2. Mapping DTO to Entity
        Customer customer = Customer.builder()
                .identityNumber(request.getIdentityNumber())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encoding the password using the
                                                                         // PasswordEncoder bean defined in
                                                                         // SecurityConfig.
                .build();

        // 3. Saving to the Database
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        // Implementation for retrieving a customer by their ID. This method will use
        // the customerRepository to find the customer and return their details. If the
        // customer is not found, it will throw an exception.

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }
}

// The Service layer is responsible for containing the business logic of the
// application. In this class, we have a method `createCustomer` that takes a
// `CustomerCreateRequest` DTO as input. The method performs business
// validations to ensure that there are no existing customers with the same
// identity number or email. If the validations pass, it maps the DTO to a
// `Customer` entity and saves it to the database using the
// `CustomerRepository`. The method returns the saved `Customer` entity.
