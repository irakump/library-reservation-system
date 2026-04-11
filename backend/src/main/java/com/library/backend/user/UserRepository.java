package com.library.backend.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for User entity persistence operations.
 * Provides CRUD operations and custom queries for User data access.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Finds a user by email address.
     *
     * @param email user email
     * @return optional user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks whether a user exists with the given email.
     *
     * @param email user email
     * @return true if email is already registered, otherwise false
     */
    Boolean existsByEmail(String email);
}
