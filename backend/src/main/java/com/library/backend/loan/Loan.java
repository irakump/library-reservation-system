package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "due_date", nullable = false)
    private Timestamp dueDate;

    @Column(name = "return_date")
    private Timestamp returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false)
    private Book book;

    public Loan() {}

    public int getLoanId() {
        return loanId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }
}
