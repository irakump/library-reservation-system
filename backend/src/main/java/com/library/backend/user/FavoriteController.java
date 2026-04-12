package com.library.backend.user;

import com.library.backend.book.Book;
import com.library.backend.book.BookDTO;
import com.library.backend.book.BookService;
import com.library.backend.security.AuthorizationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * REST controller for managing user's favorite books.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/users/{userId}/favorites")
public class FavoriteController {

    /**
     * Service layer for favorite book operations.
     */
    private final FavoriteService service;
    private final BookService bookService;

    /**
     * Constructor.
     *
     * @param favoriteService service for favorite operations
     */
    public FavoriteController(final FavoriteService favoriteService, final BookService bookService) {
        this.service = favoriteService;
        this.bookService = bookService;
    }

    /**
     * Returns all favorite books of a user.
     *
     * @param userId user id
     * @param request HTTP request (used for authorization)
     * @return set of favorite books
     */
    @GetMapping("/{lang}")
    public List<BookDTO> getFavorites(@PathVariable final int userId,
                                  @PathVariable final String lang,
                                  final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        return bookService.localizeBooks(service.getFavorites(userId), lang);
    }

    /**
     * Adds a book to user's favorites.
     *
     * @param userId user id
     * @param isbn book ISBN
     * @param request HTTP request (used for authorization)
     */
    @PostMapping("/{isbn}")
    public void addFavorite(@PathVariable final int userId,
                            @PathVariable final String isbn,
                            final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        service.addFavorite(userId, isbn);
    }

    /**
     * Removes a book from user's favorites.
     *
     * @param userId user id
     * @param isbn book ISBN
     * @param request HTTP request (used for authorization)
     */
    @DeleteMapping("/{isbn}")
    public void removeFavorite(@PathVariable final int userId,
                               @PathVariable final String isbn,
                               final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        service.removeFavorite(userId, isbn);
    }

}
