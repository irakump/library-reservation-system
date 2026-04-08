package com.library.backend.book;

import com.library.backend.loan.Loan;
import lombok.Getter;
import lombok.Setter;


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

    public BookDTO(Book b) {
        this.isbn = b.getIsbn();
        this.title = b.getTitle();
        this.image = b.getTitle();
        this.description = b.getDescription();
        this.year = b.getYear();
        this.language = b.getLanguage();
        this.genre = b.getGenre();
        this.availability = b.getAvailability();
    }
}
