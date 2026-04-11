package com.library.backend.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    /**
     * UserRepository for database access.
     */
    private final UserRepository userRepo;

    /**
     * Constructor.
     *
     * @param userRepository user repository
     */
    public UserService(final UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    /**
     * Gets all users.
     *
     * @return list of users as DTOs
     */
    public List<UserResponseDTO> getAllUsers() {
        return ((List<User>) userRepo.findAll())
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    /**
     * Gets user by id.
     *
     * @param userId user id
     * @return user DTO
     */
    public UserResponseDTO getUserById(final Integer userId) {
       User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        "User not found: " + userId));
       return new UserResponseDTO(user);
    }

    /**
     * Gets user by email.
     * Used with registration to check if email already exists
     *
     * @param email user email
     * @return user DTO
     */
    public UserResponseDTO getUserByEmail(final String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        "User not found: " + email));
        return new UserResponseDTO(user);
    }

}
