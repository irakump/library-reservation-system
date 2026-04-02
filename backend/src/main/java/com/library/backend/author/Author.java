package com.library.backend.author;

import com.library.backend.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name ="author_id")
    private Integer authorId;

    @Getter
    @Setter
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "first_name_ja", length = 100, nullable = false)
    private String firstNameJa;

    @Getter
    @Setter
    @Column(name = "last_name_ja", length = 100, nullable = false)
    private String lastNameJa;

    @Getter
    @Setter
    @Column(name = "first_name_ar", length = 100, nullable = false)
    private String firstNameAr;

    @Getter
    @Setter
    @Column(name = "last_name_ar", length = 100, nullable = false)
    private String lastNameAr;

    @Getter
    @Setter
    // Many-to-Many relationship (inverse side)
    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors") // Prevent infinite recursion during JSON serialization
    private List<Book> books;

    public Author() {}

    public Author(String firstName, String lastName, String firstNameJa, String lastNameJa, String firstNameAr, String lastNameAr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstNameJa = firstNameJa;
        this.lastNameJa = lastNameJa;
        this.firstNameAr = firstNameAr;
        this.lastNameAr = lastNameAr;
    }
}
