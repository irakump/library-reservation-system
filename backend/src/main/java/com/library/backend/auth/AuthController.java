package com.library.backend.auth;

import com.library.backend.security.JwtUtil;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    /*
    @GetMapping("/api/register")
    public String register() {
        return "register";
    }

     */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /*
    // Handle user registration form submit request = create new user
    @PostMapping(value = "/api/register", consumes = "application/json")//"/api/register/save")
    public User createUser(@RequestBody User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

     */

    // Handle user registration form submit request = create new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {

        // Check if email exists in the database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Create new user
        User user = new User(
                registerDto.getEmail(),
                registerDto.getNickname(),
                passwordEncoder.encode(registerDto.getPassword())
        );

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {

        // Find user by email
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElse(null);

        // Validate user and password
        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getUserId(),
                user.getRole().toString()
        );


        // Return response with token and user info
        LoginResponse response = new LoginResponse(
                token,
                user.getEmail(),
                user.getNickname(),
                user.getUserId(),
                user.getRole().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
