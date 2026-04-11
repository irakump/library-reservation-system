package com.library.backend.book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing Book entities through API calls.
 *
 */
public interface BookRepository extends JpaRepository<Book, String> {
    /**
     * Finds books by genre.
     *
     * @param genre genre to filter by
     * @return iterable of matching books
     */
    Iterable<Book> findByGenre(String genre);

    /**
     * Finds books by publication year.
     *
     * @param year publication year
     * @return iterable of matching books
     */
    Iterable<Book> findByYear(int year);

    /**
     * Finds books by language.
     *
     * @param language language code
     * @return iterable of matching books
     */
    Iterable<Book> findByLanguage(String language);
}
