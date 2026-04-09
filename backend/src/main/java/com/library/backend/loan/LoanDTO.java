package com.library.backend.loan;

import com.library.backend.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanDTO {
    private int loanId;
    private LocalDateTime createdAt;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    private String isbn;
    private int userId;
    private String title;
    private String image;
    private String description;
    private int year;
    private String language;
    private String genre;

    public LoanDTO(Loan l) {
        this.loanId = l.getLoanId();
        this.createdAt = l.getCreatedAt();
        this.dueDate = l.getDueDate();
        this.returnDate = l.getReturnDate();
        this.userId = l.getUser().getUserId();
        this.isbn = l.getBook().getIsbn();

        Book book = l.getBook();
        this.title = book.getTitle();
        this.image = book.getImage();
        this.description = book.getDescription();
        this.year = book.getYear();
        this.language = book.getLanguage();
        this.genre = book.getGenre();
    }
}
