package com.library.backend.book;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // filter books by matching genre (if true) AND language (if true) AND any year (if true)
    public List<Book> findByFilters(String genre, List<Integer> years, String language, Boolean available, String search_term) {
        return ((List<Book>) bookRepo.findAll()).stream()
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
}
