package com.library.backend.genre;

import com.library.backend.util.LocalizationUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Genre entity class.
 * Provides endpoints for API.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/genre")
public class GenreController {

    /**
     * GenreRepository dependency.
     */
    private final GenreRepository repository;

    /**
     * Creates GenreController class with dependencies.
     *
     * @param repository GenreRepository dependency
     */
    public GenreController(final GenreRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns all localized genres.
     *
     * @param lang localization language code, e.g. "en-US" or "ja-JP"
     * @return list of localized genre DTOs
     */
    @GetMapping("/all/{lang}")
    public List<GenreDTO> getAllGenres(@PathVariable final String lang) {
        final List<Genre> genres = repository.findAll();
        return genres.stream().map(genre -> {
            final GenreDTO dto = new GenreDTO(genre);
            dto.setGenre(LocalizationUtil.getLocalizedGenre(genre, lang));
            return dto;
        }).toList();
    }

    /**
     * Returns a single localized genre.
     *
     * @param genre English name of genre
     * @param lang localization language code, e.g. "en-US" or "ja-JP"
     * @return localized genre DTO
     */
    @GetMapping("/{genre}/{lang}")
    public GenreDTO getGenreByName(
            @PathVariable final String genre,
            @PathVariable final String lang
    ) {
        return repository.findById(genre)
                .map(g -> {
                    final GenreDTO dto = new GenreDTO(g);
                    dto.setGenre(LocalizationUtil.getLocalizedGenre(g, lang));
                    return dto;
                })
                .orElse(null);
    }
}
