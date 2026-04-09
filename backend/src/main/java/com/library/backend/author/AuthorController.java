package com.library.backend.author;

import com.library.backend.util.LocalizationUtil;
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
    @GetMapping("/all/{lang}")
    public List<AuthorDTO> getAllAuthors(@PathVariable String lang) {
        List<Author> authors = repository.findAll();

        return authors.stream()
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO(author);
                    dto.setFirstName(LocalizationUtil.getLocalizedAuthorFirstName(author, lang));
                    dto.setLastName(LocalizationUtil.getLocalizedAuthorLastName(author, lang));
                    return dto;
                })
                .toList();
    }

    // Get author by ID
    @GetMapping("/{id}/{lang}")
    public AuthorDTO getAuthorById(@PathVariable Integer id, @PathVariable String lang) {
        return repository.findById(id)
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO(author);
                    dto.setFirstName(LocalizationUtil.getLocalizedAuthorFirstName(author, lang));
                    dto.setLastName(LocalizationUtil.getLocalizedAuthorLastName(author, lang));
                    return dto;
                })
                .orElse(null);
    }
}
