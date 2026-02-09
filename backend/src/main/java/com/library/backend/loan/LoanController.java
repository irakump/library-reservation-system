package com.library.backend.loan;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanRepository repository;

    public LoanController(LoanRepository repository) {
        this.repository = repository;
    }

    // Get all loans
    @GetMapping
    public List<LoanDTO> getAllLoans() {
        Iterable<Loan> loans = repository.findAll();

        return StreamSupport.stream(loans.spliterator(), false)
                .map(LoanDTO::new)
                .toList();
    }

    // Get loan by id
    @GetMapping("/{loanId}")
    public LoanDTO getLoanById(@PathVariable Integer loanId) {
        return repository.findById(loanId)
                .map(LoanDTO::new)
                .orElse(null);
    }

    // Get loans by user's id
    @GetMapping("/user/{userId}")
    public List<LoanDTO> getLoansByUserId(@PathVariable Integer userId) {
        return repository.findByUserUserId(userId)
                .stream()
                .map(LoanDTO::new)
                .toList();
    }


    /*
    // Get active loans (not returned) by user's id and return date
    @GetMapping("/user/{userId}/active")
    public LoanDTO getLoansByUserIdAndReturnDate(@PathVariable Integer userId, Timestamp returnDate) {
        // findByUserUserIdAndReturnDate
        // TODO: check returndate = NULL

    }

    // Get returned loans by user's id and return date
    @GetMapping("/user/{userId}/returned")
    public LoanDTO getLoansByUserIdAndReturnDate(@PathVariable Integer userId, Timestamp returnDate) {
        // findByUserUserIdAndReturnDate
        // TODO: check returndate != NULL

    }

     */

}
