package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity representing a loan of a book to a user in the library system.
 */
@Getter
@Entity
@Table(name = "loan")
public class Loan {

    /** Unique id for the loan */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;

    /** Creation time of loan. Automatically created by the database*/
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    /** The day book must be returned*/
    @Setter
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    /** The actual day of return*/
    @Setter
    @Column(name = "return_date")
    private LocalDateTime returnDate;


    /** The user who borrowed the book*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** The book that has been loaned*/
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false)
    private Book book;


    /** Default constructor required by JPA */
    protected Loan() {
        //This constructor is intentionally empty
    }

    /**
     * Creates a new loan instance.
     *
     * @param dueDate the date by which the book must be returned
     * @param user the user borrowing the book
     * @param book the book being borrowed
     */
    public Loan(final LocalDate dueDate, final User user, final Book book) {
        this.dueDate = dueDate;
        this.user = user;
        this.book = book;
    }

}
