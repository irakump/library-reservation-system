package com.library.backend.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {
    Iterable<Book> findByGenre(String genre);
    Iterable<Book> findByYear(int year);
    Iterable<Book> findByLanguage(String language);

    // todo: rework (AND/OR, etc.)
    // books by any matching filter combination (genre, year, language)
    @Query("select book from Book book where " +
            "(:g is null or book.genre in :g) and " +
            "(:y is null or book.year in :y) and " +
            "(:l is null or book.language in :l)")
    List<Book> findByFilters(
            @Param("g") List<String> g,
            @Param("y") List<Integer> y,
            @Param("l") List<String> l
    );
}
