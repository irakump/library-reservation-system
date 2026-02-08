package com.library.backend.language;

import jakarta.persistence.*;

@Entity
@Table(name= "language")
public class Language {

    @Id
    @Column(name = "language", length= 50)
    private String language;

    public Language() {}

    public Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
