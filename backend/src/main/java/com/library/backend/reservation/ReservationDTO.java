package com.library.backend.reservation;

import com.library.backend.author.AuthorDTO;
import com.library.backend.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

// Reservation Data Transfer Object
@Getter
@Setter
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

    private List<AuthorDTO> authors;

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

        this.authors = book.getAuthors() != null
                ? book.getAuthors().stream()
                .map(AuthorDTO::new)
                .toList()
                : List.of();
    }

}
