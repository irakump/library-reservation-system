package com.library.backend.user;


import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
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

    @GetMapping("/isbns")
    public List<String > getIsbns(@PathVariable int userId) {
        return service.getFavoriteIsbnsByUserId(userId);
    }


}
