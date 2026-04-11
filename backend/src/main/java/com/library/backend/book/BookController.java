package com.library.backend.book;
import com.library.backend.util.LocalizationUtil;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for Book entity class.
 * Provides endpoints for API.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/book")
public class BookController {
    /**
     * BookService dependency.
     */
    private final BookService bookService;

    /**
     * BookRepository dependency.
     */
    private final BookRepository repository;

    /**
     * Creates BookController class with dependencies.
     *
     * @param repository BookRepository dependency
     * @param bookService BookService dependency
     */
    public BookController(final BookRepository repository, final BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    /**
     * Returns all books with localized title and description.
     * Does not contain filtering, and therefor not used for search results.
     *
     * @param lang localization language code, e.g. "en-US" or "ja-JP"
     * @return list of localized book DTOs
     */
    @GetMapping("/{lang}")
    public List<BookDTO> getAllBooks(@PathVariable final String lang) {
        final List<Book> books =  repository.findAll();

        return books.stream().map(book -> {
            final BookDTO dto = new BookDTO(book);
            dto.setTitle(LocalizationUtil.getLocalizedTitle(book, lang));
            dto.setDescription(LocalizationUtil.getLocalizedDescription(book, lang));
            return dto;
        }).toList();
    }

    /**
     * Returns a single book by ISBN.
     *
     * @param isbn ISBN code of book
     * @return single book by ISBN code
     */
    @GetMapping("/{isbn}/")
    public Book getBookByIsbn(@PathVariable final String isbn) {
        return repository.findById(isbn).orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));}

    /**
     * Returns all years used by all books in the library.
     *
     * @return list of years
     */
    @GetMapping("/years")
    public List<Integer> getAllBookYears() {
        final List<Book> books = repository.findAll();
        return books.stream()
            .map(Book::getYear)
            .distinct()
            .toList();
    }

    /**
     * Returns all books with genre.
     *
     * @param genre genre to filter by
     * @return list of matching books
     */
    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable final String genre) {
        return (List<Book>) repository.findByGenre(genre);
    }

    /**
     * Returns all books with release year.
     *
     * @param year year to filter by
     * @return list of matching books
     */
    @GetMapping("/year/{year}")
    public List<Book> getBooksByYear(@PathVariable final int year) {
        return (List<Book>) repository.findByYear(year);
    }

    /**
     * Returns all books with language.
     *
     * @param language language to filter by
     * @return list of matching books
     */
    @GetMapping("/language/{language}")
    public List<Book> getBooksByLanguage(@PathVariable final String language) {
        return (List<Book>) repository.findByLanguage(language);
    }

    /**
     * Returns localized list of books matching all filters (any combination).
     * If no books match all filters, returns empty list.
     * Filtered books are localized to language passed as argument.
     *
     * @param genre optional genre filter
     * @param years optional year filter
     * @param language optional language filter
     * @param available optional availability filter
     * @param searchTerm optional search term (for book title, author, and description)
     * @param lang language code for localization of results
     * @return list of books matching all filters
     */
    @GetMapping("/filter/{lang}")
    public List<BookDTO> getBooksByFilters(
            @RequestParam(required = false) final String genre,
            @RequestParam(required = false) final List<Integer> years,
            @RequestParam(required = false) final String language,
            @RequestParam(required = false) final Boolean available,
            @RequestParam(required = false) final String searchTerm,
            @PathVariable final String lang
    ) {
        final List<Book> book = bookService.findByFilters(genre, years, language, available, searchTerm);
        return bookService.localizeBooks(book, lang);
    }
}
