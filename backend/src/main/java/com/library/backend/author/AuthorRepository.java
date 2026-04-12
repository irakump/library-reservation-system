package com.library.backend.author;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Author data.
 */
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
