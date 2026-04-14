package com.banking.mini_banking.security;

import javax.crypto.SecretKey;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service // Indicates that this class is a service component in the Spring framework. It
         // is responsible for containing business logic related to JWT (JSON Web Token)
         // handling in the mini banking application.
public class JwtService {

    private static final String SECRET_KEY = "MiniBankingAppSuperSecretKeyForJwtGenerationDoNotShare";
    // A secret key used for signing and verifying JWTs. In a real application, this
    // should be stored securely and not hard-coded in the source code.
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours in milliseconds

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        // Generates a signing key from the secret key using the HMAC
        // SHA algorithm. This key will be used to sign and verify JWTs.
    }

    // Method to generate a JWT token for a given email. The token will include the
    // email as the subject, the issued date, and the expiration date. It will be
    // signed using the signing key generated from the secret key.
    public String generateToken(String email) {
        return Jwts.builder()
                // Sets the subject of the JWT to the provided email. The subject is a standard
                // claim in JWTs that typically represents the principal (user) for whom the
                // token is issued.
                .subject(email)
                // Sets the issued date of the JWT to the current time.
                // This indicates when the token was created.
                .issuedAt(new Date(System.currentTimeMillis()))
                // Sets the expiration date of the JWT to a future time based on the defined
                // expiration time. This indicates when the token will expire and no longer be
                // valid.
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // Signs the JWT using the signing key generated from the secret key. This
                // ensures the integrity and authenticity of the token, allowing it to be
                // verified later. The algorithm used for signing is determined by the type of
                // key provided (in this case, HMAC SHA).
                .signWith(getSigningKey())
                // Builds the JWT and returns it as a compact string. The resulting token can be
                // used for authentication and authorization purposes in the application.
                .compact();
    }

    public String extractUsername(String token) {
        // Method to extract the username (email) from a given JWT token. It parses the
        // token using the signing key to verify its integrity and then retrieves the
        // subject claim, which contains the email of the user.
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        // Method to validate a given JWT token. It attempts to parse the token using
        // the
        // signing key. If the token is valid and not expired, it returns true. If the
        // token is invalid (e.g., signature does not match, token is expired), it
        // catches
        // the exception and returns false.
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

// The JwtService class is responsible for generating JWT tokens for the mini
// banking application. It defines a method `generateToken` that takes an email
// as input and creates a JWT token containing the email as the subject, along
// with the issued date and expiration date. The token is signed using a secret
// key to ensure its integrity and authenticity. The generated token can be used
// for authentication and authorization purposes in the application, allowing
// users to securely access protected resources. The class also includes methods
// to extract the username from a token and to validate the token's integrity
// and expiration.
