package com.library.backend.genre;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing Genre entities through API calls.
 */
public interface GenreRepository extends JpaRepository<Genre, String> {

}
