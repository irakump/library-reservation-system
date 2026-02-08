package com.library.backend.reservation;

// Reservation Data Transfer Object
public class ReservationDTO {
    private int reservationId;
    private String status;
    private String bookIsbn;
    private int userId;

    public ReservationDTO(Reservation r) {
        this.reservationId = r.getReservationId();
        this.status = r.getStatus().name();
        this.bookIsbn = r.getBook().getIsbn();
        this.userId = r.getUser().getUserId();
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getStatus() {
        return status;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public int getUserId() {
        return userId;
    }
}
