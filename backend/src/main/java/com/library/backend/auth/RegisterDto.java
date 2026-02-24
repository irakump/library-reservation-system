package com.library.backend.auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String nickname;
    private String email;
    private String password;
}
