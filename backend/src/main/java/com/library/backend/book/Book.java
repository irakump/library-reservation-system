package com.library.backend.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.backend.author.Author;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "book")
public class Book {
    @Getter
    @Id
    @Column(name = "isbn", length = 20)
    private String isbn;

    @Getter
    @Column(name = "book_title", length = 100, nullable = false)
    private String title;

    @Getter
    @Column(name = "book_title_ja", length = 100, nullable = false)
    private String title_ja;

    @Getter
    @Column(name = "book_title_ar", length = 100, nullable = false)
    private String title_ar;

    @Getter
    @Column(name = "publishing_year", nullable = false)
    private int year;

    @Getter
    @Column(name = "image_name", length = 100)
    private String image;

    @Getter
    @Column(name = "description", length = 5000, nullable = false)
    private String description;

    @Getter
    @Column(name = "description_ja", length = 5000, nullable = false)
    private String description_ja;

    @Getter
    @Column(name = "description_ar", length = 5000, nullable = false)
    private String description_ar;

    @Getter
    @Column(name = "genre", length = 50, nullable = false)
    private String genre;

    @Getter
    @Column(name = "language", length = 100, nullable = false)
    private String language;

    @Setter
    @Column(name = "available", nullable = false)
    private boolean available;

    @Getter
    @ManyToMany
    @JoinTable(
        name = "writes",
        joinColumns = @JoinColumn(name = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;


    public Book(String isbn, String title, String title_ja, String title_ar, int year, String description, String description_ja, String description_ar, String genre, String language, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.title_ja = title_ja;
        this.title_ar = title_ar;
        this.year = year;
        this.description = description;
        this.description_ja = description_ja;
        this.description_ar = description_ar;
        this.genre = genre;
        this.language = language;
        this.available = available;
    }

    public Book() {
    }


    public boolean getAvailability() {return available;}

}
