package com.library.backend.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    // Token generation tests
    @Test
    void testGenerateToken_Success() {
        String email = "test@test.com";
        int userId = 123;
        String role = "User";

        String token = jwtUtil.generateToken(email, userId, role);

        assertNotNull(token,"Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
        assertTrue(token.startsWith("eyJ"), "JWT token should start with 'eyJ");

        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "JWT token should have three parts (header.payload.signature)");
    }

    @Test
    void testGenerateToken_WithAdminRole() {
        String email = "admin@test.com";
        int userId = 1;
        String role = "admin";

        String token = jwtUtil.generateToken(email, userId, role);
        assertNotNull(token);
        String extractedRole = jwtUtil.extractRole(token);
        assertEquals("admin", extractedRole, "Role should be 'admin");
    }

    // Token validation tests
    @Test
    void testValidateToken_ValidToken() {
        String email = "user@test.com";
        String token = jwtUtil.generateToken(email, 345, "User");

        boolean isValid = jwtUtil.validateToken(token, email);
        assertTrue(isValid, "Token should be valid for the correct email");
    }

    @Test
    void testValidateToken_WrongEmail() {
        String email = "user@test.com";
        String wrongEmail = "wrong@test.com";
        String token = jwtUtil.generateToken(email, 345, "User");

        boolean isValid = jwtUtil.validateToken(token, wrongEmail);

        assertFalse(isValid, "Token should be invalid for wrong email");

    }

    @Test
    void testValidateToken_NullToken() {
        String email = "user@test.com";

        assertThrows(Exception.class, () -> {
            jwtUtil.validateToken(null, email);
        }, "Should throw exception for null token");
    }


    // Claim extraction tests
    @Test
    void testExtractEmail_Success() {
        String email = "test@example.com";
        String token = jwtUtil.generateToken(email, 765, "user");

        String extractedEmail = jwtUtil.extractEmail(token);

        assertEquals(email, extractedEmail, "Extracted email should match original");
    }

    @Test
    void testExtractUserId_Success() {
        String email = "user@test.com";
        int userId = 999;
        String token = jwtUtil.generateToken(email, userId, "user");

        Integer extractedUserId = jwtUtil.extractUserId(token);

        assertEquals(userId, extractedUserId, "Extracted user Id should mathch  original");
    }

    @Test
    void testExtractRole_UserRole() {
        String token = jwtUtil.generateToken("user@test.com", 123, "user");
        String role = jwtUtil.extractRole(token);

        assertEquals("user", role, "Role should be user");
    }
}