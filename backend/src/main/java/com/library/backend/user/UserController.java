package com.library.backend.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        List<User> all = (List<User>) repository.findAll();
        all.forEach(u -> System.out.println(u.getEmail() + " " + u.getRole()));
        return all;
        //return (List<User>) repository.findAll();
    }

    // Get user by id
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return repository.findById(userId).orElse(null);
    }

    // Get user by email (used with registration to check if email (unique) already exists)
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return repository.findByEmail(email).orElse(null);
    }
}
