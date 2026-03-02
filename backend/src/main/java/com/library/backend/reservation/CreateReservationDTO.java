package com.library.backend.reservation;

public class CreateReservationDTO {
    private int userId;
    private String isbn;

    public int getUserId() {
        return userId;
    }

    public String getIsbn()  {
        return isbn;
    }
}
