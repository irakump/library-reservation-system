package com.library.backend.book;

import com.library.backend.author.Author;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "book_title", length = 100, nullable = false)
    private String title;

    @Column(name = "publishing_year", nullable = false)
    private int year;

    @Column(name = "image_name", length = 100)
    private String image;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "genre", length = 50, nullable = false)
    private String genre;

    @Column(name = "language", length = 100, nullable = false)
    private String language;

    @Column(name = "available", nullable = false)
    private boolean available;

    @ManyToMany
    @JoinTable(
        name = "writes",
        joinColumns = @JoinColumn(name = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book() {}


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {return title;}

    public int getYear() {return year;}

    public String getImage() {return image;}

    public String getDescription() {return description;}

    public String getGenre() {return genre;}

    public String getLanguage() {return language;}

    public boolean getAvailability() {return available;}

    //public void setIsbn(String book) {this.isbn = isbn;}

    public List<Author> getAuthors() {
        return authors;
    }
}
