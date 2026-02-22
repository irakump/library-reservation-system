package com.library.backend.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer> { // Primary key in users table is Integer

    @Query("select book.isbn from User user join user.favorites book where user.userId = :userId")
    List<String> findFavoriteIsbnsByUserId(@Param("userId") int userId);
}
