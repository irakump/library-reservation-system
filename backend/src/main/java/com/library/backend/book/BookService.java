package com.library.backend.book;

import java.util.List;
import java.util.Optional;

import com.library.backend.author.AuthorDTO;
import com.library.backend.genre.Genre;
import com.library.backend.genre.GenreRepository;
import com.library.backend.language.Language;
import com.library.backend.language.LanguageController;
import com.library.backend.language.LanguageRepository;
import com.library.backend.util.LocalizationUtil;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepo;
    private final GenreRepository genreRepo;
    private final LanguageRepository languageRepo;

    public BookService(BookRepository bookRepo, GenreRepository genreRepo, LanguageRepository languageRepo) {
        this.bookRepo = bookRepo;
        this.genreRepo = genreRepo;
        this.languageRepo = languageRepo;
    }

    // filter books by matching genre (if true) AND language (if true) AND any year (if true)
    public List<Book> findByFilters(String genre, List<Integer> years, String language, Boolean available, String search_term) {
        return (bookRepo.findAll()).stream()
                .filter(book -> genre == null || (book.getGenre() != null && book.getGenre().equalsIgnoreCase(genre)))
                .filter(book -> years == null || years.isEmpty() || years.contains(book.getYear()))
                .filter(book -> language == null || (book.getLanguage() != null && book.getLanguage().equalsIgnoreCase(language)))
                .filter(book -> available == null || book.getAvailability() == available)
                .filter(book -> {
                    if (search_term == null || search_term.isEmpty()) return true;

                    String search_term_lower = search_term.toLowerCase();
                    // check if title, author, or description contains search term
                    if (book.getTitle().toLowerCase().contains(search_term_lower) ||
                            book.getAuthors().stream().anyMatch(author -> author.getFirstName().toLowerCase().contains(search_term_lower) || author.getLastName().toLowerCase().contains(search_term_lower)) ||
                            book.getDescription().toLowerCase().contains(search_term_lower)) {
                        return true;
                    }
                    return false;
                })
                .toList();

    }

    public List<BookDTO> localizeBooks(List<Book> books, String lang) {
        return books.stream().map(book -> {
            Genre genre = genreRepo.findById(book.getGenre()).orElseThrow(() -> new IllegalStateException("Genre not found: " + book.getGenre() + "for" + book.getIsbn()));
            Language language = languageRepo.findById(book.getLanguage()).orElseThrow(() -> new IllegalStateException("Langauge not found"));

            BookDTO dto = new BookDTO(book);
            dto.setTitle(LocalizationUtil.getLocalizedTitle(book, lang));
            dto.setDescription(LocalizationUtil.getLocalizedDescription(book, lang));
            dto.setGenre(LocalizationUtil.getLocalizedGenre(genre, lang));
            dto.setLanguage(LocalizationUtil.getLocalizedLanguage(language, lang));

            dto.setAuthors(book.getAuthors().stream()
                    .map(author -> {
                        AuthorDTO authorDto = new AuthorDTO(author);
                        authorDto.setFirstName(LocalizationUtil.getLocalizedAuthorFirstName(author, lang));
                        authorDto.setLastName(LocalizationUtil.getLocalizedAuthorLastName(author, lang));
                        return authorDto;
                    })
                    .toList());

            return dto;
        }).toList();
    }
}
