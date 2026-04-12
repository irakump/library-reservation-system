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

    public LoanDTO(final Loan loan) {
        this.loanId = loan.getLoanId();
        this.createdAt = loan.getCreatedAt();
        this.dueDate = loan.getDueDate();
        this.returnDate = loan.getReturnDate();
        this.userId = loan.getUser().getUserId();
        this.isbn = loan.getBook().getIsbn();

        final Book book = loan.getBook();
        this.title = book.getTitle();
        this.image = book.getImage();
        this.description = book.getDescription();
        this.year = book.getYear();
        this.language = book.getLanguage();
        this.genre = book.getGenre();
    }
}
