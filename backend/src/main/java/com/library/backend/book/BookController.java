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
        return repository.findById(isbn).orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));}

    // years used by books
    @GetMapping("/years")
    public List<Integer> getAllBookYears() {
        List<Book> books = (List<Book>) repository.findAll();
        return books.stream()
            .map(Book::getYear)
            .distinct()
            .toList();
    }

    // books by genre
    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return (List<Book>) repository.findByGenre(genre);
    }

    // books by year
    @GetMapping("/year/{year}")
    public List<Book> getBooksByYear(@PathVariable int year) {
        return (List<Book>) repository.findByYear(year);
    }

    // books by year
    @GetMapping("/language/{language}")
    public List<Book> getBooksByLanguage(@PathVariable String language) {
        return (List<Book>) repository.findByLanguage(language);
    }

    // books by filters (any combination)
    @GetMapping("/filter")
    public List<Book> getBooksByFilters(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) List<Integer> years,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) String search_term
    ) {
        return repository.findByFilters(genre, years, language, available, search_term);
    }
}
