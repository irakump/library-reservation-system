package com.library.backend.genre;

import jakarta.persistence.*;

@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @Column(name = "genre", length = 50)
    private String genre;

    @Column(name = "genre_ja", length = 50)
    private String genreJa;

    @Column(name = "genre_ar", length = 50)
    private String genreAr;

    public Genre() {}

    public Genre(String genre, String genreJa, String genreAr) {
        this.genre = genre;
        this.genreJa = genreJa;
        this.genreAr = genreAr;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenreJa() {
        return genreJa;
    }

    public void setGenreJa(String genreJa) {
        this.genreJa = genreJa;
    }

    public String getGenreAr() {
        return genreAr;
    }

    public void setGenreAr(String genreAr) {
        this.genreAr = genreAr;
    }
}
