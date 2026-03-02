package com.library.backend.reservation;

public class CancelReservationDTO {
    private int reservationId;
    private int userId;
    private String isbn;

    public int getUserId() {
        return userId;
    }

    public String getIsbn()  {
        return isbn;
    }

    public int getReservationId() {
        return reservationId;
    }
}
