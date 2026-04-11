package com.library.backend.auth;

import com.library.backend.security.JwtUtil;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

/**
 * Service for handling user authentication,
 * including registration and login.
 */
@Service
public class AuthService {
    /** Repository for user data access. */
    private final UserRepository userRepo;
    /** Encoder for hashing passwords. */
    private final PasswordEncoder passwordEncoder;
    /** Utility for generating JWT tokens. */
    private final JwtUtil jwtUtil;

    /**
     * Constructor for AuthService.
     * @param repo the user repository
     * @param encoder the password encoder
     * @param jwt the JWT utility
     */
    public AuthService(final UserRepository repo,
                       final PasswordEncoder encoder,
                       final JwtUtil jwt) {
        this.userRepo = repo;
        this.passwordEncoder = encoder;
        this.jwtUtil = jwt;
    }

    /**
     * Register a new user.
     * @param registerDto the registration data
     * @return the created user
     */
    @Transactional
    public User registerUser(final RegisterDto registerDto) {
        // Check if email exists in the database
        if (userRepo.existsByEmail(registerDto.getEmail())) {
          throw new RuntimeException("Email is already taken!");
        }

        // Create new user
        User user = new User(
                registerDto.getEmail(),
                registerDto.getNickname(),
                passwordEncoder.encode(registerDto.getPassword())
        );

        return userRepo.save(user);
    }

    /**
     * Log in a user and returns a JWT token.
     * @param loginDto the login credentials
     * @return login response with token and user info
     */
    public LoginResponse loginUser(final LoginDto loginDto) {
        // Find user by email
        User user = userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"));

        // Validate password
        if (!passwordEncoder.matches(
                loginDto.getPassword(),
                user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getUserId(),
                user.getRole().toString()
        );

        String createdAt = null;
        if (user.getCreatedAt() != null) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "d.M.yyyy");
            createdAt = user.getCreatedAt().
                    toLocalDateTime().
                    format(formatter);
        }

        // Return response with token and user info
        return new LoginResponse(
                token,
                user.getEmail(),
                user.getNickname(),
                user.getUserId(),
                user.getRole().toString(),
                createdAt
        );
    }
}
