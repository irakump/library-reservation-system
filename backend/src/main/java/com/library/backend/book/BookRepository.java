package com.library.backend.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {
    Iterable<Book> findByGenre(String genre);
    Iterable<Book> findByYear(int year);
    Iterable<Book> findByLanguage(String language);

    // filter books by matching genre (if true) AND language (if true) AND any year (if true)
    @Query("select distinct book from Book book " +
            "left join book.authors a " +
            "where " +
            "(:genre is null or lower(book.genre) = lower(:genre)) and " +
            "(:years is null or book.year in :years) and " +
            "(:language is null or lower(book.language) = lower(:language)) and " +
            "(:available is null or book.available = :available) " +
            "and " +
            "((:search_term is null or lower(book.title) like lower(concat('%', :search_term, '%')) or " +
            "lower(a.firstName) like lower(concat('%', :search_term, '%')) or " +
            "lower(a.lastName) like lower(concat('%', :search_term, '%')) or " +
            "lower(book.description) like lower(concat('%', :search_term, '%'))))")
    List<Book> findByFilters(
            @Param("genre") String genre,
            @Param("years") List<Integer> years,
            @Param("language") String language,
            @Param("available") Boolean available,
            @Param("search_term") String search_term
    );
}
