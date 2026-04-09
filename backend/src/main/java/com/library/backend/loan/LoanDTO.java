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
        this.title = l.getBook().getTitle();
        this.image = l.getBook().getTitle();
        this.description = l.getBook().getDescription();
        this.year = l.getBook().getYear();
        this.language = l.getBook().getLanguage();
        this.genre = l.getBook().getGenre();
    }
}
