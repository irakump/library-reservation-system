package com.library.backend.book;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {this.repository = repository;}

    //all books
    @GetMapping
    public List<Book> getAllBooks() {
        return  (List<Book>) repository.findAll();
    }

    //books by isbn
    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return repository.findById(isbn).orElse(null);}
}
