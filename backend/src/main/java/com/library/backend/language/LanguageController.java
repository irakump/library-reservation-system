package com.library.backend.language;

import com.library.backend.util.LocalizationUtil;
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

    @GetMapping("/all/{lang}")
    public List<LanguageDTO> getAllLanguages(@PathVariable String lang) {
        List<Language> languages = (List<Language>) repository.findAll();
        return languages.stream().map(language -> {
            LanguageDTO dto = new LanguageDTO(language);
            dto.setLanguage(LocalizationUtil.getLocalizedLanguage(language, lang));
            return dto;
        }).toList();
    }

    @GetMapping("/{language}/{lang}")
    public LanguageDTO getLanguageByName(
            @PathVariable String language,
            @PathVariable String lang) {
        return repository.findById(language)
                .map(l -> {
                    LanguageDTO dto = new LanguageDTO(l);
                    dto.setLanguage(LocalizationUtil.getLocalizedLanguage(l, lang));
                    return dto;
                })
                .orElse(null);
    }

}
