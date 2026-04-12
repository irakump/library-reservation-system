package com.library.backend.auth;

import lombok.Data;

/**
 * Data transfer object for login requests.
 */
@Data
public class LoginDto {
    /** User's email address. */
    private String email;
    /** User's password. */
    private String password;
}
