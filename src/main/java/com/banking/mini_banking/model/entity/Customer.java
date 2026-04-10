package com.banking.mini_banking.model.entity;

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

}

// This class represents the Customer entity in the mini banking application. It
// is annotated with JPA annotations to define how it maps to the database
// table. The class includes fields for the customer's identity number, full
// name, email, and password, along with appropriate constraints such as
// uniqueness and non-nullability. Lombok annotations are used to reduce
// boilerplate code by automatically generating getters, setters, constructors,
// and a builder pattern for the class.
