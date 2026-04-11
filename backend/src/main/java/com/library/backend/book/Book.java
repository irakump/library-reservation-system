package com.library.backend.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.backend.author.Author;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Book entity class containing information of a single book.
 */
@Entity
@Table(name = "book")
public class Book {

    /**
     * Max length for ISBN code.
     */
    private static final int ISBN_MAX_LENGTH = 20;

    /**
     * Max length for book title.
     */
    private static final int TITLE_MAX_LENGTH = 100;

    /**
     * Max length for image name.
     */
    private static final int IMAGE_NAME_MAX_LENGTH = 100;

    /**
     * Max length for book description.
     */
    private static final int DESCRIPTION_MAX_LENGTH = 100;

    /**
     * Max length for genre.
     */
    private static final int GENRE_MAX_LENGTH = 50;

    /**
     * Max length for language.
     */
    private static final int LANGUAGE_MAX_LENGTH = 100;

    /**
     * ISBN code.
     */
    @Getter
    @Id
    @Column(name = "isbn", length = ISBN_MAX_LENGTH)
    private String isbn;

    /**
     * English title.
     */
    @Getter
    @Column(name = "book_title", length = TITLE_MAX_LENGTH, nullable = false)
    private String title;

    /**
     * Japanese title.
     */
    @Getter
    @Column(name = "book_title_ja", length = TITLE_MAX_LENGTH, nullable = false)
    private String titleJa;

    /**
     * Arabic title.
     */
    @Getter
    @Column(name = "book_title_ar", length = TITLE_MAX_LENGTH, nullable = false)
    private String titleAr;

    /**
     * Publishing year.
     */
    @Getter
    @Column(name = "publishing_year", nullable = false)
    private int year;

    /**
     * Name of image used for book cover.
     */
    @Getter
    @Column(name = "image_name", length = IMAGE_NAME_MAX_LENGTH)
    private String image;

    /**
     * English description.
     */
    @Getter
    @Column(name = "description", length = DESCRIPTION_MAX_LENGTH, nullable = false)
    private String description;

    /**
     * Japanese description.
     */
    @Getter
    @Column(name = "description_ja", length = DESCRIPTION_MAX_LENGTH, nullable = false)
    private String descriptionJa;

    /**
     * Arabic description.
     */
    @Getter
    @Column(name = "description_ar", length = DESCRIPTION_MAX_LENGTH, nullable = false)
    private String descriptionAr;

    /**
     * Genre of book, e.g. "History" or "Biography".
     */
    @Getter
    @Column(name = "genre", length = GENRE_MAX_LENGTH, nullable = false)
    private String genre;

    /**
     * Language of book, e.g. "English" or "Finnish".
     */
    @Getter
    @Column(name = "language", length = LANGUAGE_MAX_LENGTH, nullable = false)
    private String language;

    /**
     * Availability status of book, e.g. true or false.
     * Updates when book is loaned and returned.
     */
    @Getter
    @Setter
    @Column(name = "available", nullable = false)
    private boolean available;

    /**
     * Join Author entity class to this Book entity class through Writes table.
     */
    @Getter
    @ManyToMany
    @JoinTable(
        name = "writes",
        joinColumns = @JoinColumn(name = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;

    /**
     * Creates a Book class with all required fields.
     *
     * @param isbn ISBN code of book
     * @param title english title of book
     * @param titleJa japanese title of book
     * @param titleAr arabic title of book
     * @param year release year of book
     * @param description english description of book
     * @param descriptionJa japanese description of book
     * @param descriptionAr arabic description of book
     * @param genre genre of book
     * @param language language of book
     * @param available availability status of book
     */
    public Book(
            final String isbn,
            final String title,
            final String titleJa,
            final String titleAr,
            final int year,
            final String description,
            final String descriptionJa,
            final String descriptionAr,
            final String genre,
            final String language,
            final boolean available
    ) {
        this.isbn = isbn;
        this.title = title;
        this.titleJa = titleJa;
        this.titleAr = titleAr;
        this.year = year;
        this.description = description;
        this.descriptionJa = descriptionJa;
        this.descriptionAr = descriptionAr;
        this.genre = genre;
        this.language = language;
        this.available = available;
    }

    /**
     * Empty constructor required by Spring Boot.
     */
    public Book() {
        // Empty constructor needed for Spring Boot
    }
}
