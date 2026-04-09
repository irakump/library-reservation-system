package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.genre.Genre;
import com.library.backend.genre.GenreRepository;
import com.library.backend.language.Language;
import com.library.backend.language.LanguageRepository;
import com.library.backend.notifications.MailService;
import com.library.backend.notifications.NotificationService;
import com.library.backend.reservation.Reservation;
import com.library.backend.reservation.ReservationDTO;
import com.library.backend.reservation.ReservationService;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import com.library.backend.util.LocalizationUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class LoanService {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final LoanRepository loanRepo;
    private final ReservationService reservationService;
    private final NotificationService notificationService;
    private final GenreRepository genreRepo;
    private final LanguageRepository languageRepo;

    public LoanService(UserRepository userRepo, BookRepository bookRepo, LoanRepository loanRepo, @Lazy ReservationService reservationService, NotificationService notificationService,GenreRepository genreRepo, LanguageRepository languageRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.loanRepo = loanRepo;
        this.reservationService = reservationService;
        this.notificationService = notificationService;
        this.genreRepo = genreRepo;
        this.languageRepo = languageRepo;
    }

    // Localize loans
    public List<LoanDTO> localizeLoans(List<Loan> loans, String lang) {
        return loans.stream().map(l -> {
            Genre genre = genreRepo.findById(l.getBook().getGenre())
                    .orElseThrow(() -> new IllegalStateException("Genre not found"));
            Language language = languageRepo.findById(l.getBook().getLanguage())
                    .orElseThrow(() -> new IllegalStateException("Language not found"));

            LoanDTO dto = new LoanDTO(l);
            dto.setTitle(LocalizationUtil.getLocalizedTitle(l.getBook(), lang));
            dto.setDescription(LocalizationUtil.getLocalizedDescription(l.getBook(), lang));
            dto.setGenre(LocalizationUtil.getLocalizedGenre(genre, lang));
            dto.setLanguage(LocalizationUtil.getLocalizedLanguage(language, lang));
            return dto;
        }).toList();
    }

    //Get all loans
    public List<LoanDTO> getAllLoans() {
        Iterable<Loan> loans = loanRepo.findAll();

        return StreamSupport.stream(loans.spliterator(), false)
                .map(LoanDTO::new)
                .toList();
    }

    //Get loans by user
    public List<Loan> getActiveLoansByUser(int userId) {
        return loanRepo.findByUserUserId(userId)
                .stream()
                .filter(loan -> loan.getReturnDate() == null)
                .toList();
    }

    public List<Loan> getLoanHistoryByUser(int userId) {
        return loanRepo.findByUserUserId(userId)
                .stream()
                .filter(loan -> loan.getReturnDate() != null)
                .toList();
    }

    //Create new loan
    @Transactional
    public LoanDTO createLoan(CreateLoanDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("user not found: "));
        Book book = bookRepo.findById(dto.getIsbn()).orElseThrow(() -> new RuntimeException("isbn not found: "));

        LocalDate dueDate = LocalDate.now().plusWeeks(2);
        Loan loan = new Loan(dueDate, user, book);
        loanRepo.save(loan);

        book.setAvailable(false);
        bookRepo.save(book);

        return new LoanDTO(loan);
    }

    //Return a loan
    @Transactional
    public void returnLoan(ReturnLoanDTO dto) {
        Book book = bookRepo.findById(dto.getIsbn()).orElseThrow(() -> new RuntimeException("book not found"));
        Loan loan = loanRepo.findById(dto.getLoanId()).orElseThrow(() -> new RuntimeException("loan not found"));

        LocalDateTime returnDate = LocalDateTime.now();
        loan.setReturnDate(returnDate);
        loanRepo.save(loan);

        //book.setAvailable(true);  // processReservationQueue is handling availability
        bookRepo.save(book);

        // Update reservation queue
        reservationService.processReservationQueue(book, loan);
        notificationService.notifyDueDate(loan.getUser(), book);
    }
}
