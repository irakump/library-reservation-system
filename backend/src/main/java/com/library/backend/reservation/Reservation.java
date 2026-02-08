package com.library.backend.reservation;

import com.library.backend.user.User;
import com.library.backend.book.Book;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false)
    private Book book;

    public Reservation() {}

    public int getReservationId() {
        return reservationId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public enum Status {
        active, not_active
    }
}
