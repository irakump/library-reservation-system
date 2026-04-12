package com.library.backend.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user authentication.
 * Provides endpoints for user registration and login.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    /** Service handling authentication logic. */
    private final AuthService authService;

    /**
     * Constructor for AuthController.
     *
     * @param service The authentication service
     */
    public AuthController(final AuthService service) {
        this.authService = service;
    }

    /**
     * Register a new user.
     * @param registerDto the registration data
     * @return success message or error
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody final RegisterDto registerDto) {
        try {
            authService.registerUser(registerDto);
            return ResponseEntity.ok(
                    "User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Logs in an existing user.
     * @param loginDto the login credentials
     * @return login response with token or error
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody final LoginDto loginDto) {
        try {
            final LoginResponse response = authService.loginUser(loginDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
