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
    @Query("select distinct book from Book book " +
            "left join book.authors a " +
            "where " +
            "(:genres is null or book.genre in :genres) and " +
            "(:years is null or book.year in :years) and " +
            "(:languages is null or book.language in :languages) and " +
            "(:available is null or book.available = :available) and " +
            "(:title_author is null or lower(book.title) like lower(concat('%', :title_author, '%')) " +
            "or lower(a.firstName) like lower(concat('%', :title_author, '%')) " +
            "or lower(a.lastName) like lower(concat('%', :title_author, '%')))")
    List<Book> findByFilters(
            @Param("genres") List<String> genres,
            @Param("years") List<Integer> years,
            @Param("languages") List<String> languages,
            @Param("available") Boolean available,
            @Param("title_author") String title_author
    );
}
