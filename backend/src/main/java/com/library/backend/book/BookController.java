package com.library.backend.book;
import com.library.backend.util.LocalizationUtil;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    private final BookRepository repository;

    public BookController(BookRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    //NOT used for search results
    @GetMapping("/{lang}")
    public List<BookDTO> getAllBooks(@PathVariable String lang) {
        List<Book> books =  repository.findAll();

        return books.stream().map(book -> {
            BookDTO dto = new BookDTO(book);
            dto.setTitle(LocalizationUtil.getLocalizedTitle(book, lang));
            dto.setDescription(LocalizationUtil.getLocalizedDescription(book, lang));
            return dto;
        }).toList();
    }

    //books by isbn
    @GetMapping("/{isbn}/")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return repository.findById(isbn).orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));}

    // years used by books
    @GetMapping("/years")
    public List<Integer> getAllBookYears() {
        List<Book> books = repository.findAll();
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

    // books by filters (any combination) USED FOR FETCHING ALL SEARCH RESULTS
    // first finds books that matches filters (or all books if zero filters)
    // then returns list of BookDTOs containing localized book data based on path variable lang
    @GetMapping("/filter/{lang}")
    public List<BookDTO> getBooksByFilters(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) List<Integer> years,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) String search_term,
            @PathVariable String lang
    ) {
        List<Book> b = bookService.findByFilters(genre, years, language, available, search_term);
        return bookService.localizeBooks(b, lang);
    }
}
