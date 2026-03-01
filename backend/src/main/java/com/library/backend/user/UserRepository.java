package com.library.backend.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> { // Primary key in users table is Integer
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
