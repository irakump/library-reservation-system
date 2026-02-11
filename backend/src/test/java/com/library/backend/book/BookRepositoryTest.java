package com.library.backend.book;


import com.library.backend.author.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;


    @Test
    void shouldFindBooks() {

        repository.saveAll(List.of(
        new Book("1111", "Test book1", 2021, "Test book", "biography", "english", true),
        new Book("2222", "Test book2", 2010, "Test book", "fantasy", "english", true),
        new Book("3333", "Test book3", 2004, "Test book", "history", "english", true),
        new Book("4444", "Test book4", 2024, "Test book", "history", "finnish", true),
        new Book("5555", "Test book5", 2024, "Test book", "fantasy", "english", true),
        new Book("6666", "Test book6", 2010, "Test book", "fantasy", "english", true)));


        List<Book> newBooks = (List<Book>) repository.findAll();

        assertThat(newBooks).hasSize(6);
    }

    @Test
    void shouldntFindBooks() {
        List<Book> books = (List<Book>) repository.findAll();
        assertThat(books).isEmpty();
    }

    @Test
    void shouldFindByIsbn() {
        Book b = new Book("1111", "Test book1", 2021, "Test book", "biography", "english", true);
        Book book = repository.save(b);

        Optional<Book> result = repository.findById(book.getIsbn());

        assertThat(result.get().getIsbn()).isEqualTo("1111");

    }

    @Test
    void shouldReturnEmptyIfBookNotFound() {
        Optional<Book> result = repository.findById("2299464");

        assertThat(result).isEmpty();
    }
}















