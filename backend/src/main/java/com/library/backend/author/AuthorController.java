package com.library.backend.author;

import com.library.backend.util.LocalizationUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controller for retrieving author data.
 */

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    /** Repository for author data access. */
    private final AuthorRepository repository;

    /**
     * Constructor for AuthorController.
     * @param repo the author repository
     */
    public AuthorController(final AuthorRepository repo) {
        this.repository = repo;
    }

    /**
     * Returns all authors with localized names.
     * @param lang the language code
     * @return list of localized author DTOs
     */
    @GetMapping("/all/{lang}")
    public List<AuthorDTO> getAllAuthors(
            @PathVariable final String lang) {
        List<Author> authors = repository.findAll();

        return authors.stream()
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO(author);
                    dto.setFirstName(LocalizationUtil
                                    .getLocalizedAuthorFirstName(
                                            author, lang));
                    dto.setLastName(LocalizationUtil.
                            getLocalizedAuthorLastName(
                                    author, lang));
                    return dto;
                })
                .toList();
    }

    /**
     * Returns an author by ID with a localized name.
     * @param id the author ID
     * @param lang the language code
     * @return localized author DTO or null
     */
    @GetMapping("/{id}/{lang}")
    public AuthorDTO getAuthorById(
            @PathVariable final Integer id,
            @PathVariable final String lang) {
        return repository.findById(id)
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO(author);
                    dto.setFirstName(LocalizationUtil.
                            getLocalizedAuthorFirstName(author, lang));
                    dto.setLastName(LocalizationUtil.
                            getLocalizedAuthorLastName(author, lang));
                    return dto;
                })
                .orElse(null);
    }
}
