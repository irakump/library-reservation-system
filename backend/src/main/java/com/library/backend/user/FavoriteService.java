package com.library.backend.user;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class FavoriteService {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;

    public FavoriteService(UserRepository userRepo, BookRepository bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @Transactional
    public void addFavorite(int userId, String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found: " + isbn));
        user.addFavorites(book);
        userRepo.save(user);
    }

    @Transactional
    public void removeFavorite(int userId, String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found: " + isbn));
        user.removeFavorites(book);
        userRepo.save(user);
    }

    @Transactional
    public Set<Book> getFavorites(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return user.getFavorites();
    }

}
