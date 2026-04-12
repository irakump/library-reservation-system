package com.library.backend.auth;

import lombok.Data;

/**
 * Data transfer object for user registration requests.
 */
@Data
public class RegisterDto {
    /** User's nickname. */
    private String nickname;
    /** User's email address. */
    private String email;
    /** User's password. */
    private String password;
}
