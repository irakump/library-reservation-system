package com.library.backend.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer> { // Primary key in users table is Integer

    @Query(value = """ 
        SELECT isbn
        FROM favorite
        WHERE user_id = :userId """, nativeQuery = true)
    List<String> findFavoriteIsbnsByUserId(@Param("userId") int userId);
}
