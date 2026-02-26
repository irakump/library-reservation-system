package com.library.backend.loan;

import java.time.LocalDateTime;

public class CreateLoanDTO {
    private int userId;
    private String isbn;

    public int getUserId() {
        return userId;
    }

    public String getIsbn()  {
        return isbn;
    }

}
