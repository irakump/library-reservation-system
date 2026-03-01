package com.library.backend.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> { // Primary key in users table is Integer
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
