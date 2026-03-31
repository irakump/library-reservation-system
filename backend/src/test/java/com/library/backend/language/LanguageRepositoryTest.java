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

    private Language createLanguage(String name, String ja, String ar) {
        Language lang = new Language(name);
        lang.setLanguageJa(ja);
        lang.setLanguageAr(ar);
        return lang;
    }

    @Test
    public void testSaveLanguage() {
        Language language = createLanguage("english", "英語","الفنلندية");
        Language saved = repository.save(language);

        assertThat(saved).isNotNull();
        assertThat(saved.getLanguage()).isEqualTo("english");
        assertThat(saved.getLanguageJa()).isEqualTo("英語");
        assertThat(saved.getLanguageAr()).isEqualTo("الفنلندية");
    }

    @Test
    public void testFindById() {
        repository.save(createLanguage("finnish", "フィンランド語", "الفنلندية"));

        Optional<Language> found = repository.findById("finnish");

        assertThat(found).isPresent();
        assertThat(found.get().getLanguage()).isEqualTo("finnish");
        assertThat(found.get().getLanguageJa()).isEqualTo("フィンランド語");
        assertThat(found.get().getLanguageAr()).isEqualTo("الفنلندية");
    }

    @Test
    public void testFindById_NotFound() {
        Optional<Language> found = repository.findById("swedish");
        assertThat(found).isEmpty();
    }

    @Test
    public void testFindAll() {
        repository.save( createLanguage("english", "英語","الفنلندية"));
        repository.save(createLanguage("finnish", "フィンランド語", "الفنلندية"));


        List<Language> languages = (List<Language>) repository.findAll();

        assertThat(languages).hasSize(2);
        assertThat(languages).extracting(Language::getLanguage)
                .containsExactlyInAnyOrder("english", "finnish");

        assertThat(languages).extracting(Language::getLanguageJa)
                .containsExactlyInAnyOrder("英語", "フィンランド語");

        assertThat(languages).extracting(Language::getLanguageAr)
                .containsExactlyInAnyOrder("الفنلندية", "الفنلندية");
    }

    @Test
    public void testDeleteLanguage() {

        repository.save(createLanguage("german","ドイツ語", "الألمانية" ));

        repository.deleteById("german");
        Optional<Language> found = repository.findById("german");

        assertThat(found).isEmpty();
    }

    @Test
    public void testCount() {
        repository.save(createLanguage("english", "英語","الفنلندية"));
        repository.save(createLanguage("finnish", "フィンランド語", "الفنلندية"));

        long count = repository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void testSetLanguage() {
        Language language = new Language();
        language.setLanguage("spanish");
        language.setLanguageJa("スペイン語");
        language.setLanguageAr("الإسبانية");

        assertThat(language.getLanguage()).isEqualTo("spanish");
        assertThat(language.getLanguageJa()).isEqualTo("スペイン語");
        assertThat(language.getLanguageAr()).isEqualTo("الإسبانية");
    }
}
