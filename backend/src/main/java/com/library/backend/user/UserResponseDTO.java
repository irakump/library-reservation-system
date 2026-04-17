package com.library.backend.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DTO for returning user data to API responses.
 */
public class UserResponseDTO {
    private Integer userId;
    private String email;
    private String nickname;
    @SuppressWarnings("java:S1068")
    private String role;
    @SuppressWarnings("java:S1068")
    private String createdAt;

    /**
     * Creates a response DTO from a User entity.
     *
     * @param user source user entity
     */
    public UserResponseDTO(final User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole().toString();

        // Format timestamp
        if (user.getCreatedAt() != null) {
            LocalDateTime dateTime = user.getCreatedAt().toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("d.M.yyyy");
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

    // Setters
    public void setUserId(final Integer id) {
        this.userId = id;
    }

    public void setEmail(final String userEmail) {
        this.email = userEmail;
    }

}
