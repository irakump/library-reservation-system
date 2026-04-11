package com.library.backend.language;

import com.library.backend.util.LocalizationUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Language entity class.
 * Provides endpoints for API.
 */
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/language")
public class LanguageController {
    /**
     * LanguageRepository dependency.
     */
    private final LanguageRepository repository;

    /**
     * Creates LanguageController class with dependencies.
     *
     * @param repository LanguageRepository dependency
     */
    public LanguageController(final LanguageRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns all localized languages.
     *
     * @param lang localization language code, e.g. "en-US" or "ja-JP"
     * @return list of localized language DTOs
     */
    @GetMapping("/all/{lang}")
    public List<LanguageDTO> getAllLanguages(@PathVariable final String lang) {
        final List<Language> languages = (List<Language>) repository.findAll();
        return languages.stream().map(language -> {
            final LanguageDTO dto = new LanguageDTO(language);
            dto.setLanguage(LocalizationUtil.getLocalizedLanguage(language, lang));
            return dto;
        }).toList();
    }

    /**
     * Returns a single localized language.
     *
     * @param language English name of language
     * @param lang localization language code, e.g. "en-US" or "ja-JP"
     * @return localized language DTO
     */
    @GetMapping("/{language}/{lang}")
    public LanguageDTO getLanguageByName(
            @PathVariable final String language,
            @PathVariable final String lang) {
        return repository.findById(language)
                .map(l -> {
                    final LanguageDTO dto = new LanguageDTO(l);
                    dto.setLanguage(LocalizationUtil.getLocalizedLanguage(l, lang));
                    return dto;
                })
                .orElse(null);
    }

}
