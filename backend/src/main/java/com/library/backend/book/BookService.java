package com.library.backend.book;

import java.util.List;
import java.util.Locale;

import com.library.backend.author.AuthorDTO;
import com.library.backend.genre.Genre;
import com.library.backend.genre.GenreRepository;
import com.library.backend.language.Language;
import com.library.backend.language.LanguageRepository;
import com.library.backend.util.LocalizationUtil;
import org.springframework.stereotype.Service;

/**
 * Service layer for Book entity class related business logic.
 * Handles filtering and localization of books.
 */
@Service
public class BookService {
    /**
     * BookRepository dependency.
     */
    private final BookRepository bookRepo;

    /**
     * GenreRepository dependency.
     */
    private final GenreRepository genreRepo;

    /**
     * LanguageRepository dependency.
     */
    private final LanguageRepository languageRepo;

    /**
     * Creates a new BookService with required dependencies.
     *
     * @param bookRepo repository for books
     * @param genreRepo repository for genres
     * @param languageRepo repository for languages
     */
    public BookService(
            final BookRepository bookRepo,
            final GenreRepository genreRepo,
            final LanguageRepository languageRepo
    ) {
        this.bookRepo = bookRepo;
        this.genreRepo = genreRepo;
        this.languageRepo = languageRepo;
    }

    /**
     * Filters books by selected filters.
     * Optional filters not included are ignored.
     *
     * @param genre optional genre filter
     * @param years optional list of publication years
     * @param language optional language filter
     * @param available optional availability filter
     * @param searchTerm optional search term (for book title, author, and description)
     * @return list of books matching all provided filters
     */
    public List<Book> findByFilters(
            final String genre,
            final List<Integer> years,
            final String language,
            final Boolean available,
            final String searchTerm
    ) {
        return bookRepo.findAll().stream()
                .filter(book -> genre == null || (book.getGenre() != null && book.getGenre().equalsIgnoreCase(genre)))
                .filter(book -> years == null || years.isEmpty() || years.contains(book.getYear()))
                .filter(book -> language == null || (book.getLanguage() != null && book.getLanguage().equalsIgnoreCase(language)))
                .filter(book -> available == null || book.isAvailable() == available)
                .filter(book -> {
                    if (searchTerm == null || searchTerm.isEmpty()) {
                        return true;
                    }

                    final String searchTermLower = searchTerm.toLowerCase(Locale.ROOT);
                    // check if title, author, or description contains search term
                    return book.getTitle().toLowerCase(Locale.ROOT).contains(searchTermLower) ||
                            book.getAuthors().stream().anyMatch(author -> author.getFirstName().toLowerCase(Locale.ROOT).contains(searchTermLower) || author.getLastName().toLowerCase(Locale.ROOT).contains(searchTermLower)) ||
                            book.getDescription().toLowerCase(Locale.ROOT).contains(searchTermLower);
                })
                .toList();
    }

    /**
     * Converts books to localized BookDTOs.
     * Localizes title, description, author, genre, and language.
     *
     * @param books list of books to localize
     * @param lang target language code, e.g. "en-US" or "ja-JP"
     * @return list of localized BookDTOs
     */
    public List<BookDTO> localizeBooks(final List<Book> books, final String lang) {
        return books.stream().map(book -> localizeBook(book, lang)).toList();
    }

    /**
     * Converts books to localized BookDTOs.
     * Localizes title, description, author, genre, and language.
     *
     * @param book is the book to be localized
     * @param lang target language code, e.g. "en-US" or "ja-JP"
     * @return localized bookDTO
     */
    public BookDTO localizeBook(final Book book, final String lang) {
        final Genre genre = genreRepo.
                findById(book.getGenre())
                .orElseThrow(() ->
                        new IllegalArgumentException("Genre not found: " + book.getGenre() + " for " + book.getIsbn()));

        final Language language = languageRepo
                .findById(book.getLanguage())
                .orElseThrow(() ->
                        new IllegalStateException("Langauge not found: " + book.getLanguage() + " for " + book.getIsbn()));

        final BookDTO dto = new BookDTO(book);
        dto.setTitle(LocalizationUtil.getLocalizedTitle(book, lang));
        dto.setDescription(LocalizationUtil.getLocalizedDescription(book, lang));
        dto.setGenre(LocalizationUtil.getLocalizedGenre(genre, lang));
        dto.setLanguage(LocalizationUtil.getLocalizedLanguage(language, lang));

        dto.setAuthors(book.getAuthors().stream()
                .map(author -> {
                    final AuthorDTO authorDto = new AuthorDTO(author);
                    authorDto.setFirstName(LocalizationUtil.getLocalizedAuthorFirstName(author, lang));
                    authorDto.setLastName(LocalizationUtil.getLocalizedAuthorLastName(author, lang));
                    return authorDto;
                })
                .toList());
        return dto;
    }
}
