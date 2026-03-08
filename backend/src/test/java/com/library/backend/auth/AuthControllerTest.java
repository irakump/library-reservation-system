package com.library.backend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.backend.security.JwtAuthenticationFilter;
import com.library.backend.security.JwtUtil;
import com.library.backend.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Test HTTP endpoints

@WebMvcTest(
        value = AuthController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)
)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    RegisterDto dto = new RegisterDto();
    LoginDto loginDto = new LoginDto();

    @BeforeEach
    void setUp() {
        dto.setEmail("test@test.com");
        dto.setNickname("TestUser");
        dto.setPassword("password123");

        loginDto.setEmail("test@test.com");
        loginDto.setPassword("password123");
    }

    @Test
    void shouldRegisterUser() throws Exception {

        User user = new User("test@test.com", "TestUser", "hashedPassword123!!!");

        when(authService.registerUser(any(RegisterDto.class))).thenReturn(user);

        // Test register endpoint
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    void shouldReturn400WhenRegistrationFails() throws Exception {
        when(authService.registerUser(any(RegisterDto.class)))
                .thenThrow(new RuntimeException("Email is already taken!"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is already taken!"));
    }

    @Test
    void shouldLoginUser() throws Exception {

        // Mock LoginResponse
        LoginResponse loginResponse = new LoginResponse(
                "mock-jwt-token",
                "test@test.com",
                "TestUser",
                1,
                "user",
                null
        );

        when(authService.loginUser(any(LoginDto.class))).thenReturn(loginResponse);

        // Test login endpoint
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-jwt-token"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.nickname").value("TestUser"))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.role").value("user"));
    }

    @Test
    void shouldReturn400WhenLoginFails() throws Exception {
        when(authService.loginUser(any(LoginDto.class)))
                .thenThrow(new RuntimeException("Invalid email or password"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid email or password"));
    }
}
