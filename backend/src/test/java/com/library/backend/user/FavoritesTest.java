package com.library.backend.user;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class FavoritesTest {
    int userId;
    List<Book> books;

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private FavoriteService service;

    @BeforeEach
    void setUp() {
        User user = new User("test@email.com", "user1", "j784hkels032enc770");
        entityManager.persist(user);
        this.userId = user.getUserId();

        books = List.of(
                new Book("1111", "book1", 2021, "Test book", "biography", "english", true),
                new Book("2222", "book2", 2010, "Test book", "fantasy", "english", true),
                new Book("3333", "book3", 2004, "Test book", "history", "english", true),
                new Book("4444", "book4", 2024, "Test book", "history", "finnish", true),
                new Book("5555", "book5", 2024, "Test book", "fantasy", "english", true),
                new Book("6666", "book6", 2010, "Test book", "fantasy", "english", true));

        for (Book book : books) {
            entityManager.persist(book);
        }
        entityManager.flush();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1111", "3333", "4444"})
    void testAddFavorites(String isbn) {
        service.addFavorite(userId, isbn);
        List<String> favs = service.getFavoriteIsbnsByUserId(userId);
        assertTrue(favs.contains(isbn));
    }


    @Test
    void testRemoveFavorites() {
        for (Book book: books) {
            service.addFavorite(userId, book.getIsbn());
        }
        service.removeFavorite(userId, "1111");
        List<String> favs = service.getFavoriteIsbnsByUserId(userId);
        assertFalse(favs.contains("1111"));
    }

    @Test
    void testGetFavoritesByUserId() {
        User user2 = new User("testuser2", "user2", "jahsdyaed75b");
        entityManager.persist(user2);
        int userId2 = user2.getUserId();
        service.addFavorite(userId2, "3333"); //adds only one book for user2

        for (Book book: books) {
            service.addFavorite(userId, book.getIsbn()); //adds all books for user1
        }

        List<String> user1favorites = service.getFavoriteIsbnsByUserId(userId);
        List<String> user2favorites = service.getFavoriteIsbnsByUserId(userId2);

        assertEquals(6, user1favorites.size());
        assertEquals(1, user2favorites.size());

    }









}
