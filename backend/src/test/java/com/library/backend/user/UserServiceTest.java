package com.library.backend.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// tests for CRUD operations with mocked repository
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    private User testUser1;
    private User testUser2;

    @BeforeEach
    void setUP() {
        testUser1 = new User("user1@test.com", "User One", "hashedPassword1");
        testUser2 = new User("user2@test.com", "User Two", "hashedPass2");
    }

    @Test
    void testGetAllUsers() {
        when(userRepo.findAll()).thenReturn(Arrays.asList(testUser1, testUser2));

        List<UserResponseDTO> result = userService.getAllUsers();

        assertNotNull(result, "result should not be null");
        assertEquals(2, result.size(), "Should return 2 users");
        assertEquals("user1@test.com", result.get(0).getEmail(), "First user email should match");
        assertEquals("user2@test.com", result.get(1).getEmail(), "Second user email should match");

        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_EmptyList() {
        when(userRepo.findAll()).thenReturn(Arrays.asList());

        List<UserResponseDTO> result = userService.getAllUsers();

        assertNotNull(result, "result should not be null");
        assertEquals(0, result.size(), "Should return empty list");

        verify(userRepo, times(1)).findAll();

    }


    @Test
    void testGetUserById_success() {
        when(userRepo.findById(1)).thenReturn(Optional.of(testUser1));

        UserResponseDTO result = userService.getUserById(1);

        assertNotNull(result, "Result should not be null");
        assertEquals("user1@test.com", result.getEmail(), "Email should match");
        assertEquals("User One", result.getNickname(), "Nickname should match");

        verify(userRepo, times(1)).findById(1);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepo.findById(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(999);
        });

        assertEquals("User not found: 999", exception.getMessage());
        verify(userRepo, times(1)).findById(999);
    }



    @Test
    void testGetUserByEmail_success() {
        when(userRepo.findByEmail("user1@test.com")).thenReturn(Optional.of(testUser1));

        UserResponseDTO result = userService.getUserByEmail("user1@test.com");

        assertNotNull(result, "Result should not be null");
        assertEquals("user1@test.com", result.getEmail(), "Email should match");
        assertEquals("User One", result.getNickname(), "Nickname should match");

        verify(userRepo, times(1)).findByEmail("user1@test.com");
    }

    @Test
    void testGetUserByEmail_NotFound() {
        when(userRepo.findByEmail("notfound@test.com")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,() -> {
            userService.getUserByEmail("notfound@test.com");
        });

        assertEquals("User not found: notfound@test.com", exception.getMessage());
        verify(userRepo, times(1)).findByEmail("notfound@test.com");
    }
}
