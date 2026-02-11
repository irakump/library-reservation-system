package com.library.backend.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Test
    public void shouldSaveGenre() {
        Genre genre = repository.save(new Genre("comedy"));
        assertThat(genre).isNotNull();
        assertThat(genre.getGenre()).isEqualTo("comedy");
    }

    @Test
    public void shouldFindGenreById() {
        repository.save(new Genre("drama"));
        Iterable<Genre> genres = repository.findAll();
        assertThat(genres).extracting(Genre::getGenre).contains("drama");
    }

    @Test
    public void shouldFindAllGenres() {
        repository.save(new Genre("action"));
        repository.save(new Genre("romance"));
        repository.save(new Genre("sci-fi"));
        Iterable<Genre> genres = repository.findAll();
        assertThat(genres).extracting(Genre::getGenre).contains("action", "romance", "sci-fi");
    }

    @Test
    public void shouldDeleteGenre() {
        Genre genre = repository.save(new Genre("horror"));
        repository.delete(genre);
        assertThat(repository.findById("horror")).isEmpty();
    }
}
