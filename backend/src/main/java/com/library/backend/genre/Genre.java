package com.library.backend.genre;

import jakarta.persistence.*;

@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @Column(name = "genre", length = 50)
    private String genre;

    public Genre() {}

    public Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
