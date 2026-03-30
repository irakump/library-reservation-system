package com.library.backend.reservation;

import com.library.backend.book.Book;

import java.sql.Timestamp;

// Reservation Data Transfer Object
public class ReservationDTO {
    private int reservationId;
    private Timestamp createdAt;
    private String status;
    private String isbn;
    private int userId;

    // Book details
    private String title;
    private String image;
    private String description;
    private int year;
    private String language;
    private String genre;

    public ReservationDTO(Reservation r) {
        this.reservationId = r.getReservationId();
        this.createdAt = r.getCreatedAt();
        this.status = r.getStatus().name();
        this.isbn = r.getBook().getIsbn();
        this.userId = r.getUser().getUserId();

        Book book = r.getBook();
        this.title = book.getTitle();
        this.image = book.getIsbn(); // frontend voi koota kuvalinkin ISBN:n perusteella
        this.description = book.getDescription();
        this.year = book.getYear();
        this.language = book.getLanguage();
        this.genre = book.getGenre();
    }

    public int getReservationId() {
        return reservationId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getUserId() {
        return userId;
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

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }
}
