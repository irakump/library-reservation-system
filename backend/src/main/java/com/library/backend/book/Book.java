package com.library.backend.book;

import jakarta.persistence.*;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "isbn", length = 20)
    private String id;

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


    public Book() {}


    public String getIsbn() {
        return id;
    }

    public String getTitle() {return title;}

    public int getYear() {return year;}

    public String getImage() {return image;}

    public String getDescription() {return description;}

    public String getGenre() {return genre;}

    public String getLanguage() {return language;}

    public boolean getAvailability() {return available;}

    //public void setIsbn(String book) {this.isbn = isbn;}
}
