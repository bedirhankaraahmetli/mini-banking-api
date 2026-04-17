package com.banking.mini_banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.mini_banking.model.entity.Customer;

@Repository // Indicates that this interface is a Spring Data repository, which will be
            // automatically implemented by Spring at runtime. It provides CRUD operations
            // for the Customer entity.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // This interface extends JpaRepository, which provides CRUD operations for the
    // Customer entity. The first generic parameter is the type of the entity, and
    // the second is the type of the entity's primary key (id).

    boolean existsByIdentityNumber(String identityNumber);
    // Derived query method to check if a customer with the given identity number
    // already exists in the database.

    boolean existsByEmail(String email);
    // Derived query method to check if a customer with the given email already
    // exists in the database.

    Optional<Customer> findByEmail(String email);
    // Derived query method to find a customer by their email. This will be used in
    // the authentication process.

}

// Repository is responsible for data access and manipulation. It provides
// methods to perform CRUD operations on the Customer entity, such as saving,
// finding, and deleting customers. The custom methods `existsByIdentityNumber`
// and `existsByEmail` are used to check for the existence of a customer based
// on their identity number and email, respectively.