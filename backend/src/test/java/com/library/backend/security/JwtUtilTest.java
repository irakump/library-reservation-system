package com.library.backend.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

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



}