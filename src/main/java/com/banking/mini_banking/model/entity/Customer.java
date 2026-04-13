package com.banking.mini_banking.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity // JPA entity representing a customer in the mini banking application. This is
        // represented as a table in the database.
@Table(name = "customer") // Specifies the name of the table in the database. If not specified, it
                          // defaults to the class name.
@Getter // Lombok annotation to generate getter methods for all fields.
@Setter // Lombok annotation to generate setter methods for all fields.
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor.
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments.
@Builder // Lombok annotation to generate a builder pattern for creating instances of the
         // class.

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identity_number", unique = true, nullable = false, length = 11)
    private String identityNumber; // Turkish National Identity Number

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // One-to-many relationship with accounts. A customer can have multiple
    // accounts. The "mappedBy" attribute indicates that the "customer" field in the
    // Account class
    // owns the relationship. CascadeType.ALL means that any operation (persist,
    // merge, remove, etc.) performed on the customer will be cascaded to the
    // associated accounts. orphanRemoval = true means that if an account is removed
    // from the customer's account list, it will also be removed from the database.
    @JsonIgnore // This annotation is used to prevent serialization of the accounts field when
                // converting the Customer object to JSON. This is important to avoid
                // potential infinite recursion issues when the Customer and Account
                // entities reference each other.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;
}

// This class represents the Customer entity in the mini banking application. It
// is annotated with JPA annotations to define how it maps to the database
// table. The class includes fields for the customer's identity number, full
// name, email, and password, along with appropriate constraints such as
// uniqueness and non-nullability. Lombok annotations are used to reduce
// boilerplate code by automatically generating getters, setters, constructors,
// and a builder pattern for the class.
