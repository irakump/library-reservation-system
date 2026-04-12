package com.library.backend.loan;

import com.library.backend.security.AuthorizationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanRepository repository;
    private final LoanService loanService;

    public LoanController(final LoanRepository repository, final LoanService loanService) {
        this.repository = repository;
        this.loanService = loanService;
    }

    // Get all loans - Admin only
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans(final HttpServletRequest request) {
        AuthorizationUtil.checkAdminAccess(request);
        final List<LoanDTO> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    // Get loan by id
    @GetMapping("/{loanId}")
    public LoanDTO getLoanById(@PathVariable final Integer loanId) {
        return repository.findById(loanId)
                .map(LoanDTO::new)
                .orElse(null);
    }

    // Get active loans by user's id
    @GetMapping("/user/{userId}/{lang}")
    public ResponseEntity<List<LoanDTO>> getLoansByUser(
            @PathVariable final int userId,
            @PathVariable final String lang,
            final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        final List<Loan> loans = loanService.getActiveLoansByUser(userId);
        final List<LoanDTO> dtos = loanService.localizeLoans(loans, lang);
        return ResponseEntity.ok(dtos);
    }

    // Create new loan
    @PostMapping("/new/{lang}")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody final CreateLoanDTO request, final HttpServletRequest httpRequest, @PathVariable final String lang) {
        AuthorizationUtil.checkUserAccess(httpRequest, request.userId());
        final LoanDTO created = loanService.createLoan(request, lang);
        return ResponseEntity.ok(created);
    }

    // Change loan
    @PutMapping("/return/{lang}")
    public ResponseEntity<Void> returnLoan(@RequestBody final ReturnLoanDTO request, final HttpServletRequest httpRequest, @PathVariable final String lang) {
        AuthorizationUtil.checkUserAccess(httpRequest, request.userId());
        loanService.returnLoan(request);
        return ResponseEntity.ok().build();
    }

    // Get loan history
    @GetMapping("/user/{userId}/history/{lang}")
    public ResponseEntity<List<LoanDTO>> getLoanHistoryByUser(@PathVariable final int userId, @PathVariable final String lang, final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        final List<Loan> loans = loanService.getLoanHistoryByUser(userId);
        final List<LoanDTO> dtos = loanService.localizeLoans(loans, lang);
        return ResponseEntity.ok(dtos);
    }
}




