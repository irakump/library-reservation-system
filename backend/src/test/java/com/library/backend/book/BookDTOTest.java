package com.library.backend.book;

import com.library.backend.author.Author;
import com.library.backend.author.AuthorDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDTOTest {

    @Test
    void testCreateBookDTOObjectWithAuthor() {
        Author author = new Author("J.K.", "Rowling", "J.K.", "ローリング", "ج.ك.", "رولينغ");
        Book book = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);

        book.setAuthors(List.of(author));
        BookDTO dto = new BookDTO(book);

        assertAll(
                () -> assertEquals("Test book1", dto.getTitle()),
                () -> assertEquals(2021, dto.getYear()),
                () -> assertEquals("biography", dto.getGenre()),
                () -> assertThat(dto.getAuthors()).extracting(AuthorDTO::getLastName)
                        .containsExactlyInAnyOrder("Rowling")
        );
    }

    @Test
    void testCreateBookDTOObjectNoAuthor() {
        Book book = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);

        BookDTO dto = new BookDTO(book);

        assertAll(
                () -> assertEquals("Test book1", dto.getTitle()),
                () -> assertEquals(2021, dto.getYear()),
                () -> assertEquals("biography", dto.getGenre()),
                () -> assertThat(dto.getAuthors()).extracting(AuthorDTO::getLastName).isEmpty()
        );
    }
}
