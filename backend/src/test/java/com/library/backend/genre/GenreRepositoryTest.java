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
        Genre genre = repository.save(new Genre("comedy", "コメディ", "كوميديا"));
        assertThat(genre).isNotNull();
        assertThat(genre.getGenre()).isEqualTo("comedy");
        assertThat(genre.getGenreJa()).isEqualTo("コメディ");
        assertThat(genre.getGenreAr()).isEqualTo("كوميديا");
    }

    @Test
    public void shouldFindGenreById() {
        repository.save(new Genre("drama", "ドラマ", "دراما"));
        Iterable<Genre> genres = repository.findAll();
        assertThat(genres).extracting(Genre::getGenre).contains("drama");
        assertThat(genres).extracting(Genre::getGenreJa).contains("ドラマ");
        assertThat(genres).extracting(Genre::getGenreAr).contains("دراما");
    }

    @Test
    public void shouldFindAllGenres() {
        repository.save(new Genre("action", "アクション", "أكشن"));
        repository.save(new Genre("romance", "ロマンス", "رومانسية"));
        repository.save(new Genre("sci-fi", "サイファイ", "خيال علمي"));
        Iterable<Genre> genres = repository.findAll();
        assertThat(genres).extracting(Genre::getGenre).contains("action", "romance", "sci-fi");
        assertThat(genres).extracting(Genre::getGenreJa).contains("アクション", "ロマンス", "サイファイ");
        assertThat(genres).extracting(Genre::getGenreAr).contains("أكشن", "رومانسية", "خيال علمي");
    }

    @Test
    public void shouldDeleteGenre() {
        Genre genre = repository.save(new Genre("horror", "ホラー", "رعب"));
        repository.delete(genre);
        assertThat(repository.findById("horror")).isEmpty();
    }
}