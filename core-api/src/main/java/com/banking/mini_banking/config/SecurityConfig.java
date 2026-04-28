package com.banking.mini_banking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indicates that this class is a configuration class in the Spring framework.
               // It is responsible for defining security-related configurations for the mini
               // banking application.
@EnableWebSecurity // Enables Spring Security's web security support and provides the Spring MVC
                   // integration. This annotation is necessary to activate Spring Security in
                   // the application.
public class SecurityConfig {

        @Bean // Bean: Indicates that this method produces a bean to be managed by the Spring
              // container. In this case, it defines a bean for the PasswordEncoder, which
              // will be used for encoding passwords in the application.
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(); // Industrial-strength password encoder that uses the BCrypt hashing
                                                    // function. It is a widely used and secure way to hash passwords in
                                                    // Java
                                                    // applications.
        }

        @Bean
        // SecurityFilterChain: This method defines the security filter chain for the
        // application. It configures the HTTP security settings, such as disabling CSRF
        // protection and defining authorization rules for different endpoints.
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                        com.banking.mini_banking.security.JwtFilter jwtFilter) throws Exception {
                http // Configures the HttpSecurity object to define security settings for the
                     // application.
                                .csrf(AbstractHttpConfigurer::disable) // Disables Cross-Site Request Forgery (CSRF)
                                                                       // protection. This is
                                                                       // often done in stateless applications or APIs
                                                                       // where CSRF
                                                                       // protection is not needed.
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // Configures session management to be stateless, meaning that the application
                                // will not create or use HTTP sessions to store user authentication
                                // information. This is common in RESTful APIs where each request should be
                                // authenticated independently.
                                .authorizeHttpRequests(auth -> auth
                                                // Swagger, Customer Creation and Login Endpoints are open to all
                                                .requestMatchers("/api/v1/auth/**", "/api/v1/customers",
                                                                "/v3/api-docs/**", "/swagger-ui/**", "/api/v1/loans/**",
                                                                "/api/v1/integration/locations/**")
                                                .permitAll()
                                                // All other endpoints require authentication
                                                .anyRequest().authenticated()

                                );
                // JWT Filter is added to the security filter chain to intercept incoming HTTP
                // requests and validate the JWT token before allowing access to protected
                // resources.
                http.addFilterBefore(jwtFilter,
                                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

                return http.build(); // Builds the SecurityFilterChain based on the configured HttpSecurity settings
                                     // and returns it as a bean.
        }
}

// The SecurityConfig class is responsible for configuring the security settings
// of the mini banking application. It defines a bean for the PasswordEncoder,
// which uses BCrypt for hashing passwords, and it configures the security
// filter chain to disable CSRF protection and set session management to
// stateless. The authorization rules currently allow all requests to all
// endpoints, but in a real application, you would typically restrict access to
// certain endpoints based on user roles or permissions.
