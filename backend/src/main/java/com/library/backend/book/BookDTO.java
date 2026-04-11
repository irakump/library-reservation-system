package com.library.backend.book;

import com.library.backend.author.AuthorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data transfer object (DTO) for Book entity class.
 * Used to expose book data to other classes without exposing the Book entity completely.
 */
@Setter
@Getter
public class BookDTO {
    /**
     * ISBN code.
     */
    private String isbn;

    /**
     * Localized title.
     */
    private String title;

    /**
     * Name of image used for book cover.
     */
    private String image;

    /**
     * Localized description.
     */
    private String description;

    /**
     * Publishing year.
     */
    private int year;

    /**
     * Language of book, e.g. "English" or "Finnish".
     */
    private String language;

    /**
     * Genre of book, e.g. "History" or "Biography".
     */
    private String genre;

    /**
     * Availability status of book, e.g. true or false.
     */
    private boolean availability;

    /**
     * Authors of book.
     */
    private List<AuthorDTO> authors;

    /**
     * Creates BookDTO class from Book entity class.
     *
     * @param book source book entity
     */
    public BookDTO(final Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.image = book.getTitle();
        this.description = book.getDescription();
        this.year = book.getYear();
        this.language = book.getLanguage();
        this.genre = book.getGenre();
        this.availability = book.isAvailable();

        this.authors = book.getAuthors() != null
                ? book.getAuthors().stream()
                        .map(AuthorDTO::new)
                        .toList()
                : List.of();
    }
}
