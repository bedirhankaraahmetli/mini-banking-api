package com.banking.mini_banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.mini_banking.model.dto.CustomerCreateRequest;
import com.banking.mini_banking.model.dto.CustomerResponse;
import com.banking.mini_banking.model.entity.Customer;
import com.banking.mini_banking.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController // Indicates that this class is a REST controller in the Spring framework. It
                // will
// handle HTTP requests related to customers in the mini banking application.
@RequestMapping("/api/v1/customers") // Base URL for all customer-related endpoints. For example, to create a new
// customer, the endpoint would be /api/v1/customers.
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments (final
                         // fields).
@Tag(name = "Customer Controller", description = "Endpoints for managing customers in the mini banking application.")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping // Maps HTTP POST requests to this method. This endpoint will be used to create
                 // a
    // new customer.
    @Operation(summary = "Create a new customer", description = "Creates a new customer in the mini banking application.")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
        // The @RequestBody annotation indicates that the method parameter should be
        // bound to the body of the HTTP request. The incoming JSON will be
        // deserialized into a CustomerCreateRequest object.

        Customer createdCustomer = customerService.createCustomer(request);
        // Calls the service layer to create a new customer based on the provided
        // request data.

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        // Returns an HTTP 201 Created response with the created customer in the
        // response body.
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get customer details", description = "Retrieves the details of a customer based on the provided customer ID.")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }
}

// This class is a REST controller in the Spring framework that handles HTTP
// requests related to customers in the mini banking application. It defines
// endpoints for creating new customers and retrieving customer details. The
// controller uses the CustomerService to perform business logic operations,
// such as creating customers and fetching customer details. The endpoints are
// documented with Swagger annotations for better API documentation. The
// createCustomer method accepts a JSON request body that is deserialized into a
// CustomerCreateRequest object, while the getCustomerDetails method uses a path
// variable to identify the customer whose details are being requested.