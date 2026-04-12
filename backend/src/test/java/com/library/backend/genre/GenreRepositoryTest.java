package com.library.backend.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class GenreRepositoryTest {
    private static final String COMEDY = "comedy";
    private static final String ACTION = "action";
    private static final String ROMANCE = "romance";
    private static final String SCI_FI = "sci-fi";
    private static final String DRAMA = "drama";
    private static final String HORROR = "horror";

    private static final String COMEDY_JA = "コメディ";
    private static final String ACTION_JA = "アクション";
    private static final String ROMANCE_JA = "ロマンス";
    private static final String SCI_FI_JA = "サイファイ";
    private static final String DRAMA_JA = "ドラマ";
    private static final String HORROR_JA = "ホラー";

    private static final String COMEDY_AR = "كوميديا";
    private static final String ACTION_AR = "أكشن";
    private static final String ROMANCE_AR = "رومانسية";
    private static final String SCI_FI_AR = "خيال علمي";
    private static final String DRAMA_AR = "دراما";
    private static final String HORROR_AR = "رعب";

    @Autowired
    private GenreRepository repository;

    @Test
    void shouldSaveGenre() {
        final Genre genre = repository.save(new Genre(COMEDY, COMEDY_JA, COMEDY_AR));

        assertAll(
                () -> assertThat(genre).isNotNull(),
                () -> assertThat(genre.getGenre()).isEqualTo(COMEDY),
                () -> assertThat(genre.getGenreJa()).isEqualTo(COMEDY_JA),
                () -> assertThat(genre.getGenreAr()).isEqualTo(COMEDY_AR)
        );
    }

    @Test
    void shouldFindGenreById() {
        repository.save(new Genre(DRAMA, DRAMA_JA, DRAMA_AR));
        final Iterable<Genre> genres = repository.findAll();

        assertAll(
                () -> assertThat(genres).extracting(Genre::getGenre).contains(DRAMA),
                () -> assertThat(genres).extracting(Genre::getGenreJa).contains(DRAMA_JA),
                () -> assertThat(genres).extracting(Genre::getGenreAr).contains(DRAMA_AR)
        );
    }

    @Test
    void shouldFindAllGenres() {
        repository.save(new Genre(ACTION, ACTION_JA, ACTION_AR));
        repository.save(new Genre(ROMANCE, ROMANCE_JA, ROMANCE_AR));
        repository.save(new Genre(SCI_FI, SCI_FI_JA, SCI_FI_AR));
        final Iterable<Genre> genres = repository.findAll();

        assertAll(
                () -> assertThat(genres).extracting(Genre::getGenre).contains(ACTION, ROMANCE, SCI_FI),
                () -> assertThat(genres).extracting(Genre::getGenreJa).contains(ACTION_JA, ROMANCE_JA, SCI_FI_JA),
                () -> assertThat(genres).extracting(Genre::getGenreAr).contains(ACTION_AR, ROMANCE_AR, SCI_FI_AR)
        );
    }

    @Test
    void shouldDeleteGenre() {
        final Genre genre = repository.save(new Genre(HORROR, HORROR_JA, HORROR_AR));
        repository.delete(genre);
        assertThat(repository.findById(HORROR)).isEmpty();
    }
}
