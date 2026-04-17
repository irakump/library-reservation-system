package com.library.backend.genre;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenreDTOTest {

    @Test
    void testConstructorSetsEnglishValues() {
        Genre genre = new Genre("fantasy", "ファンタジー", "فانتازيا");
        GenreDTO dto = new GenreDTO(genre);

        assertThat(dto.getGenre()).isEqualTo("fantasy");
        assertThat(dto.getGenreKey()).isEqualTo("fantasy");
    }

    @Test
    void testSetGenreKey() {
        Genre genre = new Genre("fantasy", "ファンタジー", "فانتازيا");
        GenreDTO dto = new GenreDTO(genre);
        dto.setGenreKey("history");

        assertThat(dto.getGenreKey()).isEqualTo("history");
    }
}
