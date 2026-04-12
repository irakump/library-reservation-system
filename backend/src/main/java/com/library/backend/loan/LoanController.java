package com.library.backend.loan;

import com.library.backend.security.AuthorizationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for loan entity class.
 * Provides endpoints for retrieving, creating, and updating loan data.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    /** LoanRepository dependency*/
    private final LoanRepository repository;

    /** LoanService dependency*/
    private final LoanService loanService;

    /**
     * Creates controller with dependencies
     *
     * @param repository   the loan repository used for database access
     * @param loanService  the service handling business logic for loans
     */
    public LoanController(final LoanRepository repository, final LoanService loanService) {
        this.repository = repository;
        this.loanService = loanService;
    }

    /**
     * Retrieves all loans.
     * @param request the HTTP request containing authorization details
     * @return a response entity containing a list of all loans as DTOs
     *
     */
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans(final HttpServletRequest request) {
        AuthorizationUtil.checkAdminAccess(request);
        final List<LoanDTO> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    /**
     * Retrieves a loan by its id
     *
     * @param loanId the ID of the loan to retrieve
     * @return the corresponding {@link LoanDTO}, or null if not found
     */
    @GetMapping("/{loanId}")
    public LoanDTO getLoanById(@PathVariable final Integer loanId) {
        return repository.findById(loanId)
                .map(LoanDTO::new)
                .orElse(null);
    }

    /**
     * Retrieves all active loans for a specific user.
     *
     * @param userId  the ID of the user whose loans are requested
     * @param lang    the language code for localization
     * @param request the HTTP request containing authorization details
     * @return a {@link ResponseEntity} containing a list of active loans as DTOs
     */
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

    /**
     * Creates a new loan.
     *
     * @param request the DTO containing loan creation data
     * @param httpRequest the HTTP request containing authorization details
     * @param lang the language code for localization
     * @return a {@link ResponseEntity} containing the created loan as a DTO
     */
    @PostMapping("/new/{lang}")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody final CreateLoanDTO request, final HttpServletRequest httpRequest, @PathVariable final String lang) {
        AuthorizationUtil.checkUserAccess(httpRequest, request.userId());
        final LoanDTO created = loanService.createLoan(request, lang);
        return ResponseEntity.ok(created);
    }

    /**
     * Marks a loan as returned.
     *
     * @param request the DTO containing return information
     * @param httpRequest the HTTP request containing authorization details
     * @return a {@link ResponseEntity} with no content if the operation succeeds
     */
    @PutMapping("/return/{lang}")
    public ResponseEntity<Void> returnLoan(@RequestBody final ReturnLoanDTO request, final HttpServletRequest httpRequest, @PathVariable final String lang) {
        AuthorizationUtil.checkUserAccess(httpRequest, request.userId());
        loanService.returnLoan(request);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves the loan history of a specific user.
     *
     * @param userId the ID of the user
     * @param lang the langue of localization
     * @param request the HTTP request containing authorization details
     * @return a {@link ResponseEntity} containing a list of loan history
     */
    @GetMapping("/user/{userId}/history/{lang}")
    public ResponseEntity<List<LoanDTO>> getLoanHistoryByUser(@PathVariable final int userId, @PathVariable final String lang, final HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        final List<Loan> loans = loanService.getLoanHistoryByUser(userId);
        final List<LoanDTO> dtos = loanService.localizeLoans(loans, lang);
        return ResponseEntity.ok(dtos);
    }
}




