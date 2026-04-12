package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false)
    private Book book;

    protected Loan() {}

    public Loan(final LocalDate dueDate, final User user, final Book book) {
        this.dueDate = dueDate;
        this.user = user;
        this.book = book;
    }


    public void setReturnDate(final LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturnDate(final Timestamp returnDate) {
        this.returnDate = returnDate.toLocalDateTime();
    }

}
