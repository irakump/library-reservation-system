package com.library.backend.author;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    // Get all authors
    @GetMapping
    public List<Author> getAllAuthors() {
        return (List<Author>) repository.findAll();
    }

    // Get author by ID
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
}
