package com.library.backend.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    public void testSaveAuthor() {
        Author author = new Author("Tove", "Jansson");
        Author saved = repository.save(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getAuthorId()).isNotNull();
        assertThat(saved.getFirstName()).isEqualTo("Tove");
        assertThat(saved.getLastName()).isEqualTo("Jansson");
    }

    @Test
    public void testFindAuthorById() {
        Author author = new Author("George", "Orwell");
        Author saved = repository.save(author);

        Optional<Author> found = repository.findById(saved.getAuthorId());

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("George");
        assertThat(found.get().getLastName()).isEqualTo("Orwell");
    }

    @Test
    public void testFindById_NotFound() {
        Optional<Author> found = repository.findById(999);
        assertThat(found).isEmpty();
    }

    @Test
    public void testFindAllAuthors() {
        repository.save(new Author("Tove", "Jansson"));
        repository.save(new Author("J.K.", "Rowling"));
        repository.save(new Author("Jane", "Austen"));

        List<Author> authors = (List<Author>) repository.findAll();

        assertThat(authors).hasSize(3);
        assertThat(authors).extracting(Author::getLastName)
                .containsExactlyInAnyOrder("Rowling", "Austen", "Jansson");
    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author("J.K.", "Rowling");
        Author saved = repository.save(author);

        saved.setFirstName("William");
        saved.setLastName("Shakespeare");
        Author updated = repository.save(saved);

        assertThat(updated.getAuthorId()).isEqualTo(saved.getAuthorId());
        assertThat(updated.getFirstName()).isEqualTo("William");
        assertThat(updated.getLastName()).isEqualTo("Shakespeare");
    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author("Test", "Author");
        Author saved = repository.save(author);

        repository.deleteById(saved.getAuthorId());
        Optional<Author> found = repository.findById(saved.getAuthorId());
        assertThat(found).isEmpty();
    }

    @Test
    public void testCount() {
        repository.save(new Author("Author", "One"));
        repository.save(new Author("Author", "Two"));
        long count = repository.count();
        assertThat(count).isEqualTo(2);
    }
}
