package com.banking.mini_banking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    // The `doFilterInternal` method is overridden to implement the logic for
    // filtering incoming HTTP requests. It checks for the presence of a JWT token
    // in the "Authorization" header of the request, validates the token, and if
    // valid, sets the authentication in the security context. This allows the
    // application to identify the user making the request and grant access to
    // protected resources based on their authentication status.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Retrieves the "Authorization" header from the incoming HTTP request. This
        // header is expected to contain the JWT token in the format "Bearer <token>".
        final String authHeader = request.getHeader("Authorization");
        // Initializes variables to hold the JWT token and the user's email extracted
        // from the token.
        final String jwt;
        final String userEmail;

        // 1. Check if the "Authorization" header is present and starts with "Bearer ".
        // If not, the filter chain is continued without setting any authentication,
        // allowing the request to proceed as an unauthenticated request.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Remove the "Bearer " prefix from the header to extract the JWT token.
        // After extracting the token, the `jwtService` is used to extract the username
        // (email) from the token.
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // 3. If e-mail is extracted and there is no existing authentication in the
        // security context, the token is validated. If the token is valid, a new
        // `UsernamePasswordAuthenticationToken` is created with the user's email and an
        // empty list of authorities. The authentication details are set using the
        // request, and the authentication is stored in the security context, allowing
        // the user to access protected resources.
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtService.isTokenValid(jwt)) {
                // If the token is valid, create an authentication token with the user's email
                // and set it in the security context. This allows the application to recognize
                // the user as authenticated for the duration of the request.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userEmail, null, new ArrayList<>());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
