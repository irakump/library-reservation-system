package com.library.backend.genre;

import com.library.backend.util.LocalizationUtil;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreRepository repository;

    public GenreController(GenreRepository repository) {
        this.repository = repository;
    }

    // Get all genres
    @GetMapping("/all/{lang}")
    public List<GenreDTO> getAllGenres(@PathVariable String lang) {
        List<Genre> genres = repository.findAll();
        return genres.stream().map(genre -> {
            GenreDTO dto = new GenreDTO(genre);
            dto.setGenre(LocalizationUtil.getLocalizedGenre(genre, lang));
            return dto;
        }).toList();
    }

    // Get genre by name, localized. {genre} is English name
    @GetMapping("/{genre}/{lang}")
    public GenreDTO getGenreByName(@PathVariable String genre, @PathVariable String lang) {
        return repository.findById(genre)
                .map(g -> {
                    GenreDTO dto = new GenreDTO(g);
                    dto.setGenre(LocalizationUtil.getLocalizedGenre(g, lang));
                    return dto;
                })
                .orElse(null);
    }
}
