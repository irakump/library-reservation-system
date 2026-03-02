package com.library.backend.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserResponseDTO {
    private Integer userId;
    private String email;
    private String nickname;
    private String role;
    private String createdAt;

    public UserResponseDTO(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole().toString();

        // Format timestamp
        if (user.getCreatedAt() != null) {
            LocalDateTime dateTime = user.getCreatedAt().toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            this.createdAt = dateTime.format(formatter);
        }
    }

    // Getters
    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
