package com.library.backend.genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Genre entity class for a single genre used by Book.
 */
@Data
@Entity
@Table(name = "GENRE")
public class Genre {

    /**
     * Max length for genre name.
     */
    private static final int GENRE_MAX_LENGTH = 50;

    /**
     * English genre.
     */
    @Id
    @Column(name = "genre", length = GENRE_MAX_LENGTH)
    private String genre;

    /**
     * Japanese genre.
     */
    @Column(name = "genre_ja", length = GENRE_MAX_LENGTH)
    private String genreJa;

    /**
     * Arabic genre.
     */
    @Column(name = "genre_ar", length = GENRE_MAX_LENGTH)
    private String genreAr;

    /**
     * Empty constructor required by Spring Boot.
     */
    public Genre() {
        // Empty constructor needed for Spring Boot
    }

    /**
     * Creates a Genre class with all required fields.
     *
     * @param genre genre in English
     * @param genreJa genre in Japanese
     * @param genreAr genre in Arabic
     */
    public Genre(
            final String genre,
            final String genreJa,
            final String genreAr
    ) {
        this.genre = genre;
        this.genreJa = genreJa;
        this.genreAr = genreAr;
    }
}
