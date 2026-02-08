package com.library.backend.book;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "isbn", length = 20)
    private String isbn;

    public Book() {}

    public String getIsbn() {
        return isbn;
    }
}
