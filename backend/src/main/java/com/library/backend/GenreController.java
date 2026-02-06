package com.library.backend;

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
    @GetMapping
    public List<Genre> getAllGenres() {
        return (List<Genre>) repository.findAll();
    }

    // Get genre by name
    @GetMapping("/{genre}")
    public Genre getGenreByName(@PathVariable String genre) {
        return repository.findById(genre).orElse(null);
    }
}
