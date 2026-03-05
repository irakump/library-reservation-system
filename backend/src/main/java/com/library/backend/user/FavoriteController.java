package com.library.backend.user;


import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.security.AuthorizationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/users/{userId}/favorites")
public class FavoriteController {
    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public Set<Book> getFavorites(@PathVariable int userId, HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        return service.getFavorites(userId);
    }

    @PostMapping("/{isbn}")
    public void addFavorite(@PathVariable int userId, @PathVariable String isbn, HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        service.addFavorite(userId, isbn);
    }

    @DeleteMapping("/{isbn}")
    public void removeFavorite(@PathVariable int userId, @PathVariable String isbn, HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        service.removeFavorite(userId, isbn);
    }



}
