package com.library.backend.loan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanRepository repository;
    private final LoanService loanService;

    public LoanController(LoanRepository repository, LoanService loanService) {
        this.repository = repository;
        this.loanService = loanService;
    }

    // Get all loans
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    // Get loan by id
    @GetMapping("/{loanId}")
    public LoanDTO getLoanById(@PathVariable Integer loanId) {
        return repository.findById(loanId)
                .map(LoanDTO::new)
                .orElse(null);
    }

    // Get active loans by user's id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDTO>> getLoansByUser(@PathVariable int userId) {
        List<LoanDTO> loans = loanService.getLoansByUser(userId);
        return ResponseEntity.ok(loans);
    }

    //Create new loan
    @PostMapping("/new")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody CreateLoanDTO request) {
        LoanDTO created = loanService.createLoan(request);
        return ResponseEntity.ok(created);
    }

    //Change loan
    @PutMapping("/return")
    public ResponseEntity<Void> returnLoan(@RequestBody ReturnLoanDTO request) {
        loanService.returnLoan(request);
        return ResponseEntity.ok().build();
    }

    //Get loan history



}




