package com.banking.mini_banking.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.mini_banking.mapper.CustomerMapper;
import com.banking.mini_banking.model.dto.CustomerCreateRequest;
import com.banking.mini_banking.model.dto.CustomerResponse;
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
    private final CustomerMapper customerMapper;

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

    public CustomerResponse getCustomerById(Long id) {
        // Retrieves a customer by their ID. If the customer is not found, it throws a
        // RuntimeException with a message indicating that the customer was not found.
        // If the customer is found, it maps the Customer entity to a CustomerResponse
        // DTO using the CustomerMapper and returns it.
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return customerMapper.toResponse(customer);
    }
}

// This CustomerService class is a Spring service component that contains
// business logic related to customers in the mini banking application. It has
// two main methods: createCustomer() for creating a new customer and
// getCustomerById() for retrieving customer details by ID. The createCustomer()
// method performs business validations to ensure that a customer with the same
// identity number or email does not already exist, maps the incoming
// CustomerCreateRequest DTO to a Customer entity, encodes the password, and
// saves the customer to the database. The getCustomerById() method retrieves a
// customer by their ID, throws an exception if the customer is not found, and
// maps the Customer entity to a CustomerResponse DTO before returning it. The
// service uses a CustomerRepository for database operations, a PasswordEncoder
// for encoding passwords, and a CustomerMapper for mapping between entities and
// DTOs.
