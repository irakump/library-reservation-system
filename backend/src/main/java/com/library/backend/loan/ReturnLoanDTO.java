package com.library.backend.loan;

import java.time.LocalDateTime;

public class ReturnLoanDTO {
    private int loanId;
    private int userId;
    private String isbn;

    public ReturnLoanDTO(int loanId, int userId, String isbn) {
        this.loanId = loanId;
        this.isbn = isbn;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getIsbn()  {
        return isbn;
    }

    public int getLoanId() { return loanId;}

}