package com.library.backend.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.library.backend.author.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;


@DataJpaTest
@Import(BookService.class)
class BookFilterTest {

    @Autowired
    BookRepository repository;

    @Autowired
    BookService service;

    @Autowired
    TestEntityManager entityManager;

    Book book1;
    Book book2;
    Book book3;
    Book book4;
    Book book5;
    Book book6;

    @BeforeEach
    void setUp() {
        Author author1 = new Author("Author1", "SomeLastName");
        Author author2 = new Author("Author2", "OtherLastName");

        entityManager.persist(author1);
        entityManager.persist(author2);

        book1 = new Book("1111", "Test book1", 2021, "Test book", "biography", "english", true);
        book2 = new Book("2222", "Test book2", 2010, "Test book", "fantasy", "english", true);
        book3 = new Book("3333", "Test book3", 2004, "Test book", "history", "english", true);
        book4 = new Book("4444", "Test book4", 2024, "Test book", "history", "finnish", true);
        book5 = new Book("5555", "Test book5", 2024, "Test book", "fantasy", "english", true);
        book6 = new Book("6666", "Test book6", 2010, "Test book", "fantasy", "english", true);

        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.persist(book4);
        entityManager.persist(book5);
        entityManager.persist(book6);
    }

    @Test
    void givenNewBooks_whenDBFilteredByGenre_withGenreHistory_returnBooks() {
        List<Book> filteredBooks = (List<Book>) repository.findByGenre("history");
        assertThat(filteredBooks).containsExactly(book3, book4);
    }

    @Test
    void givenNewBooks_whenDBFilteredByYear_withYear2010_returnBooks() {
        List<Book> filteredBooks = (List<Book>) repository.findByYear(2010);
        assertThat(filteredBooks).containsExactly(book2, book6);
    }

    @Test
    void givenNewBooks_whenDBFilteredByLanguage_withLanguageEnglish_returnBooks() {
        List<Book> filteredBooks = (List<Book>) repository.findByLanguage("english");
        assertThat(filteredBooks).containsExactly(book1, book2, book3, book5, book6);
    }

    @Test
    void givenNewBooks_whenDBFiltered_withGenreHistory_withLanguageEnglish_returnBooks() {
        String genre = "history";
        List<Integer> years = null;
        String language = "english";
        Boolean availability = true;
        String search_term = null;

        List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, search_term);
        assertThat(filteredBooks).containsExactly(book3);
    }

    @Test
    void givenNewBooks_whenDBFiltered_withGenreFantasy_returnBooks() {
        String genre = "fantasy";
        List<Integer> years = null;
        String language = null;
        Boolean availability = true;
        String search_term = null;

        List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, search_term);
        assertThat(filteredBooks).containsExactly(book2, book5, book6);
    }

    @Test
    void givenNewBooks_whenDBFiltered_withGenreBiography_withGenreHistory_returnBooks() {
        String genre = "biography";
        List<Integer> years = null;
        String language = null;
        Boolean availability = true;
        String search_term = null;

        List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, search_term);
        assertThat(filteredBooks).containsExactly(book1);
    }

    @Test
    void givenNewBooks_whenDBFiltered_withAvailableFalse_returnBooks() {
        String genre = null;
        List<Integer> years = null;
        String language = null;
        Boolean availability = false;
        String search_term = null;

        List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, search_term);
        assertThat(filteredBooks).isEmpty();
    }
}