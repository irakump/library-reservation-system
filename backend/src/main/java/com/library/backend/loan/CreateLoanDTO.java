package com.library.backend.loan;

public class CreateLoanDTO {
    private int userId;
    private String isbn;

    public CreateLoanDTO(int userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }

    public int getUserId() {
        return userId;
    }

    public String getIsbn()  {
        return isbn;
    }

}
