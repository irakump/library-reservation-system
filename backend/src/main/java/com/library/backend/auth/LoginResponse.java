package com.library.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String email;
    private String nickname;
    private int userId;
    private String role;
    private String createdAt;
}
