package com.library.backend.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Get all users
    public List<UserResponseDTO> getAllUsers() {
        return ((List<User>) userRepo.findAll())
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    // Get user by id
    public UserResponseDTO getUserById(Integer userId) {
       User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
       return new UserResponseDTO(user);
    }

    // // Get user by email (used with registration to check if email (unique) already exists)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
        return new UserResponseDTO(user);
    }

}
