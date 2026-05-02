package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.book.BookDTO;
import com.library.backend.book.BookRepository;
import com.library.backend.book.BookService;
import com.library.backend.notifications.NotificationService;
import com.library.backend.reservation.ReservationService;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 *Service class responsible for handling business logic related to loans.
 */
@Service
public class LoanService {
    /** UserRepository dependency */
    private final UserRepository userRepo;

    /** BookRepository dependency*/
    private final BookRepository bookRepo;

    /** LoanRepository dependency*/
    private final LoanRepository loanRepo;

    /** ReservationQueue dependency*/
    private final ReservationService resService;

    /** NotificationService dependency*/
    private final NotificationService notifiService;

    /** BookService dependency*/
    private final BookService bookService;

    /**
     * Constructs a LoanService with all required dependencies.
     */
    public LoanService(final UserRepository userRepo, final BookRepository bookRepo, final LoanRepository loanRepo, @Lazy final ReservationService resService, final NotificationService notifiService, final BookService bookService) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.loanRepo = loanRepo;
        this.resService = resService;
        this.notifiService = notifiService;
        this.bookService = bookService;
    }

    /**
     * Localizes a list of loans into DTOs
     * Translates book title, description, genre, and language fields.
     * @param loans the list of loans to localize
     * @param lang  the language code (e.g. "en-US")
     * @return a list of localized {@link LoanDTO} objects
     * @throws IllegalStateException if genre or language is not found
     */
    public List<LoanDTO> localizeLoans(final List<Loan> loans, final String lang) {
        return loans.stream().map(l -> {
            BookDTO bookDTO = bookService.localizeBook(l.getBook(), lang);
            return new LoanDTO(l, bookDTO);
        }).toList();
    }

    /**
     * Retrieves all loans
     *
     * @return a list of all loans as {@link LoanDTO} objects
     */
    public List<LoanDTO> getAllLoans() {
        final Iterable<Loan> loans = loanRepo.findAll();

        return StreamSupport.stream(loans.spliterator(), false)
                .map(LoanDTO::new)
                .toList();
    }

    /**
     * Retrieves all active loans for a user
     *
     * @param userId the ID of the user
     * @return a list of active loans
     */
    public List<Loan> getActiveLoansByUser(final int userId) {
        return loanRepo.findByUserUserId(userId)
                .stream()
                .filter(loan -> loan.getReturnDate() == null)
                .toList();
    }

    /**
     * Retrieves all non-active loans for a user
     *
     * @param userId the ID of the user
     * @return a list of non-active loans
     */
    public List<Loan> getLoanHistoryByUser(final int userId) {
        return loanRepo.findByUserUserId(userId)
                .stream()
                .filter(loan -> loan.getReturnDate() != null)
                .toList();
    }

    /**
     * Creates a new loan for a user and marks the book as unavailable
     *
     * @param dto the data required to create a loan
     * @param lang the language code for localization
     * @return the created loan as a localized {@link LoanDTO}
     * @throws RuntimeException if the user or book is not found
     */
    @Transactional
    public LoanDTO createLoan(final CreateLoanDTO dto, final String lang) {
        final User user = userRepo.findById(dto.userId()).orElseThrow(() -> new RuntimeException("user not found: "));
        final Book book = bookRepo.findById(dto.isbn()).orElseThrow(() -> new RuntimeException("isbn not found: "));

        final LocalDate dueDate = LocalDate.now().plusWeeks(2);
        final Loan loan = new Loan(dueDate, user, book);
        loanRepo.save(loan);

        book.setAvailable(false);
        bookRepo.save(book);

        return new LoanDTO(loan, bookService.localizeBook(book, lang));
    }

    /**
     * Marks a loan as returned
     * Updates related reservation queue
     * Calls send notification method
     *
     * @param dto the data required to return a loan
     * @throws RuntimeException if the book or loan is not found
     */
    @Transactional
    public void returnLoan(final ReturnLoanDTO dto) {
        final Book book = bookRepo.findById(dto.isbn()).orElseThrow(() -> new RuntimeException("book not found"));
        final Loan loan = loanRepo.findById(dto.loanId()).orElseThrow(() -> new RuntimeException("loan not found"));

        loan.setReturnDate(LocalDateTime.now());
        loanRepo.save(loan);

        bookRepo.save(book);

        // Update reservation queue and book availability
        resService.processReservationQueue(book);
        notifiService.notifyDueDate(loan.getUser(), book);
    }

    /**
     * Checks due dates of loans every day at 13
     * Calls notify method to send a notifications
     * sets return date of loans due
     * calls method for processing queue
     */
    @Transactional
    @Scheduled(cron = "0 0 13 * * *", zone = "Europe/Helsinki")
    public void returnAllBooksByDueDate() {
        final List<Loan> dueLoans = loanRepo.findByDueDate(LocalDate.now());

        for (final Loan loan : dueLoans) {
            notifiService.notifyDueDate(loan.getUser(), loan.getBook());
            loan.setReturnDate(LocalDateTime.now());
            resService.processReservationQueue(loan.getBook());
        }
    }

    /**
     * Checks if due date is in 2 days
     * Calls notify method to send a notice
     */
    @Transactional
    @Scheduled(cron = "0 0 13 * * *", zone = "Europe/Helsinki")
    public void checkAllComingDueDate() {
        final LocalDate notifyDay = LocalDate.now().plusDays(2);
        final List<Loan> loans = loanRepo.findByDueDate(notifyDay);

        for (final Loan loan : loans) {
            notifiService.notifyComingUpDueDate(loan.getUser(), loan.getBook());
        }
    }
}
