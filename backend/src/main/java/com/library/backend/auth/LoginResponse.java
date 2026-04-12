package com.library.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object returned after successful login.
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    /** JWT authentication token. */
    private String token;
    /** User's email address. */
    private String email;
    /** User's nickname. */
    private String nickname;
    /** User's unique identifier. */
    private int userId;
    /** User's role (user or admin). */
    private String role;
    /** Account creation date. */
    private String createdAt;
}
