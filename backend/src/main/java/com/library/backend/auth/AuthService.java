package com.library.backend.auth;

import com.library.backend.security.JwtUtil;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register user
    @Transactional
    public User registerUser(RegisterDto registerDto) {
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

    // Login user
    public LoginResponse loginUser(LoginDto loginDto) {
        // Find user by email
        User user = userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Validate password
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getUserId(),
                user.getRole().toString()
        );

        // Return response with token and user info
        return new LoginResponse(
                token,
                user.getEmail(),
                user.getNickname(),
                user.getUserId(),
                user.getRole().toString()
        );
    }
}
