package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.user.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LoanDTO {
    private int loanId;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private String isbn;
    private int userId;
    private String title;
    private String image;
    private String description;
    private int year;
    private String language;

    public LoanDTO(Loan l) {
        this.loanId = l.getLoanId();
        this.createdAt = l.getCreatedAt();
        this.dueDate = l.getDueDate();
        this.returnDate = l.getReturnDate();
        this.userId = l.getUser().getUserId();
        this.isbn = l.getBook().getIsbn();
        this.title = l.getBook().getTitle();
        this.image = l.getBook().getTitle();
        this.description = l.getBook().getDescription();
        this.year = l.getBook().getYear();
        this.language = l.getBook().getLanguage();

    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public int getLoanId() {
        return loanId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }
}
