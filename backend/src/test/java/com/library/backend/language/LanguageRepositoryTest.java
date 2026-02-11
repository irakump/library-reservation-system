package com.library.backend.language;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LanguageRepositoryTest {

    @Autowired
    private LanguageRepository repository;

    @Test
    public void testSaveLanguage() {

        Language language = new Language("English");
        Language saved = repository.save(language);
        assertThat(saved).isNotNull();
        assertThat(saved.getLanguage()).isEqualTo("English");
    }

    @Test
    public void testFindById() {
        Language language = new Language("Finnish");
        repository.save(language);

        Optional<Language> found = repository.findById("Finnish");
        assertThat(found).isPresent();
        assertThat(found.get().getLanguage()).isEqualTo("Finnish");
    }

    @Test
    public void testFindById_NotFound() {
        Optional<Language> found = repository.findById("Swedish");
        assertThat(found).isEmpty();
    }

    @Test
    public void testFindAll() {
        repository.save(new Language("English"));
        repository.save(new Language("Finnish"));
        repository.save(new Language("Swedish"));

        List<Language> languages = (List<Language>) repository.findAll();

        assertThat(languages).hasSize(3);
        assertThat(languages).extracting(Language::getLanguage)
                .containsExactlyInAnyOrder("English", "Finnish", "Swedish");
    }

    @Test
    public void testDeleteLanguage() {
        Language language = new Language("German");
        repository.save(language);

        repository.deleteById("German");
        Optional<Language> found = repository.findById("German");

        assertThat(found).isEmpty();
    }

    @Test
    public void testCount() {
        repository.save(new Language("English"));
        repository.save(new Language("Finnish"));

        long count = repository.count();

        assertThat(count).isEqualTo(2);
    }

    @Test
    public void testSetLanguage() {
        Language language = new Language();
        language.setLanguage("Spanish");
        assertThat(language.getLanguage()).isEqualTo("Spanish");
    }
}
