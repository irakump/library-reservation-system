package com.library.backend.user;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Service for managing user's favorite books.
 */
@Service
public class FavoriteService {
    /**
     * UserRepository for database access.
     */
    private final UserRepository userRepo;
    /**
     * BookRepository for database access.
     */
    private final BookRepository bookRepo;

    private static final String USER_NOT_FOUND = "User not found: ";
    private static final String BOOK_NOT_FOUND = "Book not found: ";

    /**
     * Constructor.
     *
     * @param userRepository user repository
     * @param bookRepository book repository
     */
    public FavoriteService(final UserRepository userRepository,
                           final BookRepository bookRepository) {
        this.userRepo = userRepository;
        this.bookRepo = bookRepository;
    }

    /**
     * Adds a book to user's favorites.
     *
     * @param userId user id
     * @param isbn book isbn
     */
    @Transactional
    public void addFavorite(final int userId, final String isbn) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        USER_NOT_FOUND + userId));
        Book book = bookRepo.findById(isbn)
                .orElseThrow(() -> new RuntimeException(
                        BOOK_NOT_FOUND + isbn));
        user.addFavorites(book);
        userRepo.save(user);
    }

    /**
     * Removes a book from user's favorites.
     *
     * @param userId user id
     * @param isbn book isbn
     */
    @Transactional
    public void removeFavorite(final int userId, final String isbn) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        USER_NOT_FOUND + userId));
        Book book = bookRepo.findById(isbn)
                .orElseThrow(() -> new RuntimeException(
                        BOOK_NOT_FOUND + isbn));
        user.removeFavorites(book);
        userRepo.save(user);
    }

    /**
     * Returns user's favorite books.
     *
     * @param userId user id
     * @return set of favorite books
     */
    @Transactional
    public Set<Book> getFavorites(final int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        USER_NOT_FOUND + userId));
        return user.getFavorites();
    }

}
