package com.library.backend.auth;

import com.library.backend.security.JwtUtil;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Test for AuthService. Tests for Login and Register business logic

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    // Test data
    private RegisterDto registerDto;
    private LoginDto loginDto;
    private User testUser;

    @BeforeEach
    void setup() {
        registerDto = new RegisterDto();
        registerDto.setEmail("test@test.com");
        registerDto.setNickname("TestUser");
        registerDto.setPassword("password123");

        loginDto = new LoginDto();
        loginDto.setEmail("test@test.com");
        loginDto.setPassword("password123");

        testUser = new User("test@test.com", "TestUser", "hashedPassword");
    }

    // Register Tests

    @Test
    void testRegisterUser_Success() {
        when(userRepo.existsByEmail(registerDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("hashedPassword");
        when(userRepo.save(any(User.class))).thenReturn(testUser);

        User result = authService.registerUser(registerDto);

        assertNotNull(result, "Registered user should not be null");
        assertEquals("test@test.com", result.getEmail(), "Email should match");
        assertEquals("TestUser", result.getNickname(), "Nickname should match");

        verify(userRepo, times(1)).existsByEmail("test@test.com");
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        when(userRepo.existsByEmail(registerDto.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.registerUser(registerDto);
        });

        assertEquals("Email is already taken!", exception.getMessage());

        verify(userRepo, times(1)).existsByEmail("test@test.com");
        verify(passwordEncoder, never()).encode(anyString());
    }

    // Login tests
    @Test
    void testLoginUser_Success() {
        when(userRepo.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(loginDto.getPassword(), testUser.getPasswordHash())).thenReturn(true);
        when(jwtUtil.generateToken(
                testUser.getEmail(),
                testUser.getUserId(),
                testUser.getRole().toString()
        )).thenReturn("mock-jwt-token");

        LoginResponse response = authService.loginUser(loginDto);

        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
        assertEquals("test@test.com", response.getEmail());
        assertEquals("TestUser", response.getNickname());
        assertEquals("user", response.getRole());
    }

    @Test
    void testLoginUser_UserNotFound() {
        when(userRepo.findByEmail(loginDto.getEmail())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.loginUser(loginDto);
        });

        assertEquals("Invalid email or password", exception.getMessage());
        verify(userRepo, times(1)).findByEmail("test@test.com");
        verify(jwtUtil, never()).generateToken(anyString(), anyInt(), anyString());
    }

    @Test
    void testLoginUser_wrongPassword() {
        when(userRepo.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(loginDto.getPassword(), testUser.getPasswordHash())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.loginUser(loginDto);
        });

        assertEquals("Invalid email or password", exception.getMessage());
        verify(userRepo, times(1)).findByEmail("test@test.com");
        verify(passwordEncoder, times(1)).matches("password123", "hashedPassword");
        verify(jwtUtil, never()).generateToken(anyString(), anyInt(), anyString());
    }

}
