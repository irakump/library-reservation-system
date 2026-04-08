package com.library.backend.genre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
    private String genre;       // Localized name for user
    private String genreKey;    // English name

    public GenreDTO(Genre g) {
        // Set both to English genre. this.genre will be changed to localized genre in GenreController
        this.genre = g.getGenre();
        this.genreKey = g.getGenre();
    }
}
