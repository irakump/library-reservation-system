package com.library.backend.author;

import com.library.backend.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing an author.
 */
@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {

    /** Max length for name fields. */
    private static final int LANG_MAX_LENGTH = 100;

    /** Unique identifier for the author. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    /** Author's first name in English. */
    @Column(name = "first_name", length = LANG_MAX_LENGTH, nullable = false)
    private String firstName;

    /** Author's last name in English. */
    @Column(name = "last_name", length = LANG_MAX_LENGTH, nullable = false)
    private String lastName;

    /** Author's first name in Japanese. */
    @Column(name = "first_name_ja", length = LANG_MAX_LENGTH, nullable = false)
    private String firstNameJa;

    /** Author's last name in Japanese. */
    @Column(name = "last_name_ja", length = LANG_MAX_LENGTH, nullable = false)
    private String lastNameJa;

    /** Author's first name in Arabic. */
    @Column(name = "first_name_ar", length = LANG_MAX_LENGTH, nullable = false)
    private String firstNameAr;

    /** Author's last name in Arabic. */
    @Column(name = "last_name_ar", length = LANG_MAX_LENGTH, nullable = false)
    private String lastNameAr;

    /** List of books written by the author.*/
    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors") // Prevent infinite recursion
    private List<Book> books;

    /**
     * Default constructor required by JPA.
     * Must remain empty and public
     */
    public Author() {
        // Required by JPA
    }

    /**
     * Constructor with all name fields.
     * @param first first name in English.
     * @param last last name in English.
     * @param firstJa first name in Japanese.
     * @param lastJa  last name in Japanese.
     * @param firstAr first name in Arabic.
     * @param lastAr last name in Arabic.
     */
    public Author(
            final String first,
            final String last,
            final String firstJa,
            final String lastJa,
            final String firstAr,
            final String lastAr) {
        this.firstName = first;
        this.lastName = last;
        this.firstNameJa = firstJa;
        this.lastNameJa = lastJa;
        this.firstNameAr = firstAr;
        this.lastNameAr = lastAr;
    }
}
