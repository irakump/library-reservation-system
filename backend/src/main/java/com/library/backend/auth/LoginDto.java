package com.library.backend.auth;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
