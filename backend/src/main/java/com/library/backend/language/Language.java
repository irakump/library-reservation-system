package com.library.backend.language;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Language entity class for a language a book is written in.
 */
@Data
@Entity
@Table(name = "language")
public class Language {

    /**
     * Max length for language string.
     */
    private static final int LANG_MAX_LENGTH = 100;

    /**
     * Language in English.
     */
    @Id
    @Column(name = "language", length = LANG_MAX_LENGTH)
    private String language;

    /**
     * Language in Japanese.
     */
    @Column(name = "language_ja", length = LANG_MAX_LENGTH)
    private String languageJa;

    /**
     * Language in Arabic.
     */
    @Column(name = "language_ar", length = LANG_MAX_LENGTH)
    private String languageAr;

    /**
     * Empty constructor required by Spring Boot.
     */
    public Language() {
        // Empty constructor needed for Spring Boot
    }

    /**
     * Creates a Language class with all required fields.
     *
     * @param language language in English
     * @param languageJa language in Japanese
     * @param languageAr language in Arabic
     */
    public Language(final String language, final String languageJa, final String languageAr) {
        this.language = language;
        this.languageJa = languageJa;
        this.languageAr = languageAr;
    }
}
