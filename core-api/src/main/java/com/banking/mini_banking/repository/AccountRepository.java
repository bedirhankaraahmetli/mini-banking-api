package com.banking.mini_banking.repository;

import com.banking.mini_banking.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Indicates that this interface is a Spring Data repository, which will be
            // automatically implemented by Spring at runtime. It provides CRUD operations
            // for the Account entity.
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber); // Custom query method to find an account by its
                                                                 // account number. Spring Data JPA
                                                                 // will automatically generate the implementation based
                                                                 // on the method name.

}

// This interface extends JpaRepository, which provides basic CRUD operations
// for the Account entity. The generic parameters specify that this repository
// will manage Account entities and that the primary key type is Long. The
// custom method findByAccountNumber allows for retrieving an account based on
// its unique account number, which is a common requirement in banking
// applications. Spring Data JPA will automatically generate the implementation
// for this method based on the naming convention, so no additional code is
// needed to define its behavior.