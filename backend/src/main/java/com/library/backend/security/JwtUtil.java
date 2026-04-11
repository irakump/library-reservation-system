package com.library.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for generating and validating JWT tokens.
 */
@Component
public class JwtUtil {

    /**
     * Secret key used for signing JWT tokens.
     */
    private final String secretKey;

    /**
     * Constructor.
     ** @param key the secret key used for signing and verifying JWT tokens;
     *             injected from the application properties {@code jwt.secret}
     *             with a default value of {@code "default-secret"} if not set
     *
     */
    public JwtUtil(@Value("${jwt.secret:default-secret-key-3456789012-0987654321}") String key) {
        this.secretKey = key;
    }

    /**
     * Token expiration time in milliseconds.
     */
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    /**
     * Get signing key for JWT.
     *
     * @return SecretKey used for signing
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Generates JWT token for a user.
     *
     * @param email user email
     * @param userId user id
     * @param role user role
     * @return generated JWT token
     */
    public String generateToken(
            final String email, final int userId, final String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extracts email from JWT token.
     *
     * @param token JWT token
     * @return email
     */
    public String extractEmail(final String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extracts user id from JWT token.
     *
     * @param token JWT token
     * @return user id
     */
    public Integer extractUserId(final String token) {
        return extractAllClaims(token).get("userId", Integer.class);
    }

    /**
     * Extracts role from JWT token.
     *
     * @param token JWT token
     * @return role
     */
    public String extractRole(final String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    /**
     * Extracts all claims from token.
     *
     * @param token JWT token
     * @return claims
     */
    private Claims extractAllClaims(final String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Checks if token is expired.
     *
     * @param token JWT token
     * @return true if expired
     */
    public boolean isTokenExpired(final String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Validates token against email and expiration.
     *
     * @param token JWT token
     * @param email expected email
     * @return true if valid
     */
    public boolean validateToken(final String token, final String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }
}
