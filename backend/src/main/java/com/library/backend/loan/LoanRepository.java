package com.library.backend.loan;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing {@link Loan} entities.
 */
public interface LoanRepository extends CrudRepository<Loan, Integer> {
    
    /** Retrieves all loans by user
     * 
     * @param userId the unique id of user
     * @return a list of loans of the user
     */
    List<Loan> findByUserUserId(Integer userId);

    /**
     * Retrieves loans for user filtered by return status
     * active loans = {@code returnDate} is null
     *
     * @param userId     the unique identifier of the user
     * @param returnDate the return date to filter by; {@code null} for active loans
     * @return a list of loans matching the given criteria
     */
    List<Loan> findByUserUserIdAndReturnDate(Integer userId, LocalDateTime returnDate);

    /**
     * Retrieves loans filtered by timestamp.
     *
     * @param day the due date to filter loans by
     * @return a list of loans that have the specified due date
     */
    List<Loan> findByDueDate(LocalDate day);
}
