package com.banking.mini_banking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(security = { @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "bearerAuth", // Name of the security scheme, which will be referenced in the
                                     // OpenAPIDefinition.
        description = "Paste your JWT token here", // Description of the security scheme, providing instructions for
                                                   // users on how to use it.
        scheme = "bearer", // Type of the security scheme, indicating that it uses bearer tokens (JWT).
        type = SecuritySchemeType.HTTP, // Specifies that the security scheme is of type HTTP, which is commonly used
                                        // for bearer token authentication.
        in = SecuritySchemeIn.HEADER // Indicates that the JWT token should be passed in the HTTP header of the
                                     // request.
)
public class OpenApiConfig {

}

// The OpenApiConfig class is a configuration class for setting up OpenAPI
// (Swagger) documentation for the mini banking application. It defines a
// security scheme for JWT authentication, allowing users to provide their JWT
// token in the header of API requests when testing the endpoints through the
// Swagger UI. The class uses annotations to specify the security requirements
// and the details of the security scheme, ensuring that the API documentation
// accurately reflects the authentication mechanism used in the application.
// This configuration helps developers and testers understand how to
// authenticate when interacting with the API endpoints, making it easier to
// test and use the application securely.
