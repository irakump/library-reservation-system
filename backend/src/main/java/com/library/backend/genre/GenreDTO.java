package com.library.backend.genre;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object (DTO) for Genre entity class.
 * Used to expose selected genre data to other classes.
 */
@Getter
@Setter
public class GenreDTO {
    /**
     * Localized genre.
     */
    private String genre;

    /**
     * English name of genre.
     */
    private String genreKey;

    /**
     * Creates GenreDTO class from Genre entity class.
     * Sets class variables to English locale.
     * GenreController will update this.genre to localized version.
     *
     * @param genre source genre entity
     */
    public GenreDTO(final Genre genre) {
        this.genre = genre.getGenre();
        this.genreKey = genre.getGenre();
    }
}
