package com.library.backend.language;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object (DTO) for Language entity class.
 * Used to expose selected language data to other classes.
 */
@Getter
@Setter
public class LanguageDTO {
    /**
     * Localized language.
     */
    private String language;

    /**
     * English name of language.
     */
    private String languageKey;

    /**
     * Creates LanguageDTO class from Language entity class.
     * Sets class variables to English locale.
     * LanguageController will update this.language to localized version.
     *
     * @param language source language entity
     */
    public LanguageDTO(final Language language) {
      this.language = language.getLanguage();
      this.languageKey = language.getLanguage();
    }
}
