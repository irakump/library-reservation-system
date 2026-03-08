package com.library.backend.loan;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Integer> {
    List<Loan> findByUserUserId(Integer userId);

    // Get user's active or returned loans
    // If returnDate = NULL, loan is active. Otherwise, it's returned.
    List<Loan> findByUserUserIdAndReturnDate(Integer userId, Timestamp returnDate);

    List<Loan> findByDueDate(LocalDate day);
}
