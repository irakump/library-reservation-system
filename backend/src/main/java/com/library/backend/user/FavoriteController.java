package com.library.backend.user;


import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/users/{userId}/favorites")
public class FavoriteController {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;

    public FavoriteController(UserRepository userRepo, BookRepository bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public Set<Book> getFavorites(@PathVariable int userId) {
        User user = userRepo.findById(userId).orElse(null);
        assert user != null;
        return user.getFavorites();
    }

    @PostMapping("/{isbn}")
    public void addFavorite(@PathVariable int userId, @PathVariable String isbn) {
        User user = userRepo.findById(userId).orElse(null);
        Book book = bookRepo.findById(isbn).orElse(null);

        assert user != null;
        user.getFavorites().add(book);
        userRepo.save(user);
    }

    @DeleteMapping("/{isbn}/remove")
    public void removeFavorite(@PathVariable int userId, @PathVariable String isbn) {
        User user = userRepo.findById(userId).orElse(null);
        Book book = bookRepo.findById(isbn).orElse(null);

        assert user != null;
        user.getFavorites().remove(book);
        userRepo.save(user);
    }

}
