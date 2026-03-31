package com.library.backend.language;

import jakarta.persistence.*;

@Entity
@Table(name= "language")
public class Language {

    @Id
    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "language_ja", length = 100)
    private String languageJa;

    @Column(name = "language_ar", length = 100)
    private String languageAr;

    public Language() {}

    public Language(String language, String languageJa, String languageAr) {
        this.language = language;
        this.languageJa = languageJa;
        this.languageAr = languageAr;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageJa() {
        return languageJa;
    }

    public void setLanguageJa(String languageJa) {
        this.languageJa = languageJa;
    }

    public String getLanguageAr() {
        return languageAr;
    }

    public void setLanguageAr(String languageAr) {
        this.languageAr = languageAr;
    }
}
