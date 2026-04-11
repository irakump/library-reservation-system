package com.library.backend.user;

import com.library.backend.security.AuthorizationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for user-related operations.
 * Provides endpoints for retrieving users and enforcing access control.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Service layer for user operations.
     */
    private final UserService userService;

    /**
     * Creates controller with injected UserService.
     *
     * @param service user service layer
     */
    public UserController(final UserService service) {
        this.userService = service;
    }

    /**
     * Returns all users.
     * Access restricted to admin users only.
     *
     * @param request HTTP request containing authentication data
     * @return list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(
            final HttpServletRequest request) {
        AuthorizationUtil.checkAdminAccess(request);
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Returns a user by ID.
     * Access allowed for the user themselves or admin.
     *
     * @param userId user ID
     * @param request HTTP request containing authentication data
     * @return user data or 404 if not found
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable final Integer userId,
            final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        try {
            UserResponseDTO user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();   // 404 not found
        }
    }

    /**
     * Returns a user by email address.
     * Access restricted to admin users only.
     *
     * @param email user email
     * @param request HTTP request containing authentication data
     * @return user data or 404 if not found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(
            @PathVariable final String email,
            final HttpServletRequest request) {
        AuthorizationUtil.checkAdminAccess(request);
        try {
            UserResponseDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
