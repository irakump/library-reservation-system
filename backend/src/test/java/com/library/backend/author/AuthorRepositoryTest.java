package com.library.backend.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    void testSaveAuthor() {
        Author author = new Author("Tove", "Jansson", "トーベ", "ヤンソン", "توفه", "يانسون");
        Author saved = repository.save(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getAuthorId()).isNotNull();
        assertThat(saved.getFirstName()).isEqualTo("Tove");
        assertThat(saved.getLastName()).isEqualTo("Jansson");
    }

    @Test
    void testFindAuthorById() {
        Author author = new Author("George", "Orwell", "ジョージ", "オーウェル", "جورج", "أورويل");
        Author saved = repository.save(author);

        Optional<Author> found = repository.findById(saved.getAuthorId());

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("George");
        assertThat(found.get().getLastName()).isEqualTo("Orwell");
    }

    @Test
    void testFindById_NotFound() {
        Optional<Author> found = repository.findById(999);
        assertThat(found).isEmpty();
    }

    @Test
    void testFindAllAuthors() {
        repository.save(new Author("Tove", "Jansson", "トーベ", "ヤンソン", "توفه", "يانسون"));
        repository.save(new Author("J.K.", "Rowling", "J.K.", "ローリング", "ج.ك.", "رولينغ"));
        repository.save(new Author("Jane", "Austen", "ジェーン", "オースティン", "جين", "أوستن"));

        List<Author> authors = (List<Author>) repository.findAll();

        assertThat(authors).hasSize(3);
        assertThat(authors).extracting(Author::getLastName)
                .containsExactlyInAnyOrder("Rowling", "Austen", "Jansson");
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author("J.K.", "Rowling", "J.K.", "ローリング", "ج.ك.", "رولينغ");
        Author saved = repository.save(author);

        saved.setFirstName("William");
        saved.setLastName("Shakespeare");
        Author updated = repository.save(saved);

        assertThat(updated.getAuthorId()).isEqualTo(saved.getAuthorId());
        assertThat(updated.getFirstName()).isEqualTo("William");
        assertThat(updated.getLastName()).isEqualTo("Shakespeare");
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author("Test", "Author", "Test", "Author", "Test", "Author");
        Author saved = repository.save(author);

        repository.deleteById(saved.getAuthorId());
        Optional<Author> found = repository.findById(saved.getAuthorId());
        assertThat(found).isEmpty();
    }

    @Test
    void testCount() {
        repository.save(new Author("Author", "One", "Author", "One", "Author", "One"));
        repository.save(new Author("Author", "Two", "Author", "Two", "Author", "Two"));
        long count = repository.count();
        assertThat(count).isEqualTo(2);
    }
}
