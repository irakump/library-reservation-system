package com.library.backend.user;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    @MockBean
    private JavaMailSender javaMailSender;

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

    @Test
    void testAddFavorites() {
        service.addFavorite(userId, "1111");
        service.addFavorite(userId, "2222");
        service.addFavorite(userId, "3333");
        Set<Book> favs = service.getFavorites(userId);

        Set<Book> expected = Set.of(books.get(0), books.get(1), books.get(2));
        assertEquals(expected, favs);
    }


    @Test
    void testRemoveFavorites() {
        for (Book book: books) {
            service.addFavorite(userId, book.getIsbn());
        }
        service.removeFavorite(userId, "1111");
        Set<Book> favs = service.getFavorites(userId);
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

        Set<Book> user1favorites = service.getFavorites(userId);
        Set<Book> user2favorites = service.getFavorites(userId2);

        assertEquals(6, user1favorites.size());
        assertEquals(1, user2favorites.size());

    }

    @Test
    void testWrongIsbn() {
        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> service.addFavorite(userId, "pppp"));
        assertEquals("Book not found: pppp", exception.getMessage());

        RuntimeException exception1 =
                assertThrows(RuntimeException.class, () -> service.removeFavorite(userId, "pppp"));
        assertEquals("Book not found: pppp", exception1.getMessage());
    }

    @Test
    void testWrongUserId() {
        service.addFavorite(userId, "1111");

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> service.addFavorite(0001111, "1111"));
        assertEquals("User not found: " + 0001111, exception.getMessage());

        RuntimeException exception1 =
                assertThrows(RuntimeException.class, () -> service.removeFavorite(0001111, "1111"));
        assertEquals("User not found: " + 0001111, exception1.getMessage());
    }

}
