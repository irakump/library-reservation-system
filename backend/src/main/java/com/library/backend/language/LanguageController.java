package com.library.backend.language;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/language")
public class LanguageController {
    private final LanguageRepository repository;

    public LanguageController(LanguageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Language> getAllLanguage() {
        return (List<Language>) repository.findAll();
    }

    @GetMapping("/{language}")
    public Language getLanguageByName(@PathVariable String language) {
        return repository.findById(language).orElse(null);
    }

}
