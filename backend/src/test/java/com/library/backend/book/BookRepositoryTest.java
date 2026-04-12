package com.library.backend.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {
    List<Book> books;

    @Autowired
    private BookRepository repository;

    @Autowired TestEntityManager testEntityManager;

    @Test
    void testShouldFindBooks() {
        books = List.of(
                new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true),
                new Book("2222", "Test book2", "テストブック2", "كتاب اختبار 2", 2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true),
                new Book("3333", "Test book3", "テストブック3", "كتاب اختبار 3", 2004, "Test book", "テストブック", "كتاب اختبار", "history", "english", true),
                new Book("4444", "Test book4", "テストブック4", "كتاب اختبار 4", 2024, "Test book", "テストブック", "كتاب اختبار", "history", "finnish", true),
                new Book("5555", "Test book5", "テストブック5", "كتاب اختبار 5", 2024, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true),
                new Book("6666", "Test book6", "テストブック6", "كتاب اختبار 6",2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true)
        );

        for (final Book book : books) {
            testEntityManager.persist(book);
        }

        final List<Book> newBooks = repository.findAll();

        assertThat(newBooks).hasSize(6);
    }

    @Test
    void testShouldntFindBooks() {
        final List<Book> books = repository.findAll();
        assertThat(books).isEmpty();
    }

    @Test
    void testShouldFindByIsbn() {
        final Book book = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);
        testEntityManager.persist(book);

        final Optional<Book> result = repository.findById(book.getIsbn());

        assertThat(result).contains(book);
    }

    @Test
    void testShouldReturnEmptyIfBookNotFound() {
        final Optional<Book> result = repository.findById("2299464");

        assertThat(result).isEmpty();
    }
}















