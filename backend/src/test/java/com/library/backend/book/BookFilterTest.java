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
        final Author author1 = new Author("J.K.", "Rowling", "J.K.", "ローリング", "ج.ك.", "رولينغ");
        final Author author2 = new Author("Tove", "Jansson", "トーベ", "ヤンソン", "توفه", "يانسون");

        entityManager.persist(author1);
        entityManager.persist(author2);

        book1 = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);
        book2 = new Book("2222", "Test book2", "テストブック2", "كتاب اختبار 2", 2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        book3 = new Book("3333", "Test book3", "テストブック3", "كتاب اختبار 3", 2004, "Test book", "テストブック", "كتاب اختبار", "history", "english", true);
        book4 = new Book("4444", "Test book4", "テストブック4", "كتاب اختبار 4", 2024, "Test book", "テストブック", "كتاب اختبار", "history", "finnish", true);
        book5 = new Book("5555", "Test book5", "テストブック5", "كتاب اختبار 5", 2024, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        book6 = new Book("6666", "Test book6", "テストブック6", "كتاب اختبار 6",2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);

        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.persist(book4);
        entityManager.persist(book5);
        entityManager.persist(book6);
    }

    @Test
    void givenNewBooksWhenDBFilteredByGenreWithGenreHistoryReturnBooks() {
        final List<Book> filteredBooks = (List<Book>) repository.findByGenre("history");
        assertThat(filteredBooks).containsExactly(book3, book4);
    }

    @Test
    void givenNewBooksWhenDBFilteredByYearWithYear2010ReturnBooks() {
        final List<Book> filteredBooks = (List<Book>) repository.findByYear(2010);
        assertThat(filteredBooks).containsExactly(book2, book6);
    }

    @Test
    void givenNewBooksWhenDBFilteredByLanguageWithLanguageEnglishReturnBooks() {
        final List<Book> filteredBooks = (List<Book>) repository.findByLanguage("english");
        assertThat(filteredBooks).containsExactly(book1, book2, book3, book5, book6);
    }

    @Test
    void givenNewBooksWhenDBFilteredWithGenreHistoryWithLanguageEnglishReturnBooks() {
        final String genre = "history";
        final List<Integer> years = null;
        final String language = "english";
        final Boolean availability = true;
        final String searchTerm = null;

        final List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, searchTerm);
        assertThat(filteredBooks).containsExactly(book3);
    }

    @Test
    void givenNewBooksWhenDBFilteredWithGenreFantasyReturnBooks() {
        final String genre = "fantasy";
        final List<Integer> years = null;
        final String language = null;
        final Boolean availability = true;
        final String searchTerm = null;

        final List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, searchTerm);
        assertThat(filteredBooks).containsExactly(book2, book5, book6);
    }

    @Test
    void givenNewBooksWhenDBFilteredWithGenreBiographyWithGenreHistoryReturnBooks() {
        final String genre = "biography";
        final List<Integer> years = null;
        final String language = null;
        final Boolean availability = true;
        final String searchTerm = null;

        final List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, searchTerm);
        assertThat(filteredBooks).containsExactly(book1);
    }

    @Test
    void givenNewBooksWhenDBFilteredWithAvailableFalseReturnBooks() {
        final String genre = null;
        final List<Integer> years = null;
        final String language = null;
        final Boolean availability = false;
        final String searchTerm = null;

        final List<Book> filteredBooks = service.findByFilters(genre, years, language, availability, searchTerm);
        assertThat(filteredBooks).isEmpty();
    }
}