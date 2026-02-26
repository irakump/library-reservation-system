package com.library.backend.loan;

import java.time.LocalDateTime;

public class CreateLoanDTO {
    private int userId;
    private String isbn;
    private LocalDateTime dueDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIsbn()  {
        return isbn;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueTime(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
