package com.library.backend.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> { // Primary key in users table is Integer
}
