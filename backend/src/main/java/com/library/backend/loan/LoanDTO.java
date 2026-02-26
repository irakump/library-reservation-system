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
    private String bookIsbn;
    private int userId;

    public LoanDTO(Loan l) {
        this.loanId = l.getLoanId();
        this.createdAt = l.getCreatedAt();
        this.dueDate = l.getDueDate();
        this.returnDate = l.getReturnDate();
        this.userId = l.getUser().getUserId();
        this.bookIsbn = l.getBook().getIsbn();

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

    public String getBookIsbn() {
        return bookIsbn;
    }
}
