package com.library.backend.book;

import com.library.backend.author.AuthorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookDTO {
    private String isbn;
    private String title;
    private String image;
    private String description;
    private int year;
    private String language;
    private String genre;
    private boolean availability;
    private List<AuthorDTO> authors;

    public BookDTO(Book b) {
        this.isbn = b.getIsbn();
        this.title = b.getTitle();
        this.image = b.getTitle();
        this.description = b.getDescription();
        this.year = b.getYear();
        this.language = b.getLanguage();
        this.genre = b.getGenre();
        this.availability = b.getAvailability();

        this.authors = b.getAuthors() != null ?
                b.getAuthors().stream()
                        .map(AuthorDTO::new)
                        .toList()
                : List.of();
    }
}
