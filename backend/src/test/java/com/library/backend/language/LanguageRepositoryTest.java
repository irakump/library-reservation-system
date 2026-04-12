package com.library.backend.language;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class LanguageRepositoryTest {
    private static final String ENGLISH = "english";
    private static final String FINNISH = "finnish";
    private static final String SWEDISH = "swedish";
    private static final String GERMAN = "german";
    private static final String SPANISH = "spanish";

    private static final String ENGLISH_JA = "英語";
    private static final String FINNISH_JA = "フィンランド語";
    private static final String GERMAN_JA = "ドイツ語";
    private static final String SPANISH_JA = "スペイン語";

    private static final String ENGLISH_AR = "الإنجليزية";
    private static final String FINNISH_AR = "الفنلندية";
    private static final String GERMAN_AR = "الألمانية";
    private static final String SPANISH_AR = "الإسبانية";

    @Autowired
    private LanguageRepository repository;

    @Test
    void testSaveLanguage() {
        final Language saved = repository.save(new Language(ENGLISH, ENGLISH_JA, ENGLISH_AR));

        assertAll(
                () -> assertThat(saved).isNotNull(),
                () -> assertThat(saved.getLanguage()).isEqualTo(ENGLISH),
                () -> assertThat(saved.getLanguageJa()).isEqualTo(ENGLISH_JA),
                () -> assertThat(saved.getLanguageAr()).isEqualTo(ENGLISH_AR)
        );
    }

    @Test
    void testFindById() {
        repository.save(new Language(FINNISH, FINNISH_JA, FINNISH_AR));

        final Optional<Language> found = repository.findById(FINNISH);

        assertAll(
                () -> assertThat(found).isPresent(),
                () -> assertThat(found.get().getLanguage()).isEqualTo(FINNISH),
                () -> assertThat(found.get().getLanguageJa()).isEqualTo(FINNISH_JA),
                () -> assertThat(found.get().getLanguageAr()).isEqualTo(FINNISH_AR)
        );
    }

    @Test
    void testFindByIdNotFound() {
        final Optional<Language> found = repository.findById(SWEDISH);
        assertThat(found).isEmpty();
    }

    @Test
    void testFindAll() {
        repository.save(new Language(ENGLISH, ENGLISH_JA, ENGLISH_AR));
        repository.save(new Language(FINNISH, FINNISH_JA, FINNISH_AR));

        final List<Language> languages = (List<Language>) repository.findAll();

        assertAll(
                () -> assertThat(languages).extracting(Language::getLanguage)
                        .containsExactlyInAnyOrder(ENGLISH, FINNISH),
                () -> assertThat(languages).extracting(Language::getLanguageJa)
                        .containsExactlyInAnyOrder(ENGLISH_JA, FINNISH_JA),
                () -> assertThat(languages).extracting(Language::getLanguageAr)
                        .containsExactlyInAnyOrder(ENGLISH_AR, FINNISH_AR)
        );
    }

    @Test
    void testDeleteLanguage() {
        repository.save(new Language(GERMAN, GERMAN_JA, GERMAN_AR));

        repository.deleteById(GERMAN);
        final Optional<Language> found = repository.findById(GERMAN);

        assertThat(found).isEmpty();
    }

    @Test
    void testCount() {
        repository.save(new Language(ENGLISH, ENGLISH_JA, ENGLISH_AR));
        repository.save(new Language(FINNISH, FINNISH_JA, FINNISH_AR));

        final long count = repository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void testSetLanguage() {
        final Language language = new Language();
        language.setLanguage(SPANISH);
        language.setLanguageJa(SPANISH_JA);
        language.setLanguageAr(SPANISH_AR);

        assertAll(
                () -> assertThat(language.getLanguage()).isEqualTo(SPANISH),
                () -> assertThat(language.getLanguageJa()).isEqualTo(SPANISH_JA),
                () -> assertThat(language.getLanguageAr()).isEqualTo(SPANISH_AR)
        );
    }
}
