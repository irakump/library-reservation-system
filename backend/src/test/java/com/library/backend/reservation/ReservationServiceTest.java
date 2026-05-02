package com.library.backend.reservation;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.book.BookService;
import com.library.backend.genre.Genre;
import com.library.backend.genre.GenreRepository;
import com.library.backend.language.Language;
import com.library.backend.language.LanguageRepository;
import com.library.backend.loan.Loan;
import com.library.backend.loan.LoanRepository;
import com.library.backend.notifications.NotificationService;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({ReservationService.class, BookService.class})
class ReservationServiceTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private ReservationService service;

    @Autowired
    private GenreRepository genreRepo;

    @Autowired
    private LanguageRepository languageRepo;

    @MockitoBean
    private NotificationService notificationService;

    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = userRepo.save(new User("user@user.com", "User #3", "S0meHash3dP4$$.1831!Dds"));
        book = bookRepo.save(new Book("12345670", "Test book", "Test book", "Test book", 2026, "This is test book", "This is test book", "This is test book","fantasy", "english", true));
    }

    // New reservation
    @Test
    @Transactional
    void shouldCreateAndSaveReservation() {
        ReservationDTO dto = service.createReservation(user.getUserId(), book.getIsbn());

        assertThat(dto).isNotNull();
        assertThat(dto.getUserId()).isEqualTo(user.getUserId());
        assertThat(dto.getIsbn()).isEqualTo(book.getIsbn());

        // Convert iterable to stream and list
        List<Reservation> all = StreamSupport.stream(reservationRepo.findAll().spliterator(), false)
                .toList();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getStatus()).isEqualTo(Reservation.Status.active);
    }

    // Cancel reservation (set status not_active)
    @Test
    @Transactional
    void shouldSetStatusToNotActive() {
        ReservationDTO dto = service.createReservation(user.getUserId(), book.getIsbn());

        service.cancelReservation(dto.getReservationId(), user.getUserId());

        Reservation r = reservationRepo.findById(dto.getReservationId()).orElseThrow();
        assertThat(r.getStatus()).isEqualTo(Reservation.Status.not_active);
    }

    // Try to cancel other user's reservation
    @Test
    @Transactional
    void shouldFailCancelReservationIfWrongUser() {
        ReservationDTO dto = service.createReservation(user.getUserId(), book.getIsbn());

        assertThatThrownBy(() ->
                service.cancelReservation(dto.getReservationId(), 9999)
        ).isInstanceOf(RuntimeException.class);

    }

    // Try to create duplicate reservation
    @Test
    @Transactional
    void shouldNotAllowDuplicateReservation() {
        service.createReservation(user.getUserId(), book.getIsbn());

        assertThatThrownBy(() ->
                service.createReservation(user.getUserId(), book.getIsbn())
        ).isInstanceOf(RuntimeException.class);
    }

    // Try to reserve currently loaned book
    @Test
    @Transactional
    void shouldNotAllowReservationIfUserAlreadyLoanedBook() {
        Loan loan = new Loan(
                LocalDate.now().plusWeeks(2),
                user,
                book
        );

        loanRepo.save(loan);

        assertThatThrownBy(() ->
                service.createReservation(user.getUserId(), book.getIsbn())
        ).isInstanceOf(RuntimeException.class);
    }

    // Cancel non-existing reservation
    @Test
    void shouldThrowIfReservationNotFound() {
        assertThatThrownBy(() ->
                service.cancelReservation(999, user.getUserId())
        ).isInstanceOf(RuntimeException.class);
    }


    // Reservation not found
    @Test
    void shouldReturnNullIfReservationNotFound() {
        ReservationDTO dto = service.getReservationById(99999);

        assertThat(dto).isNull();
    }

    // No reservations, check and process reservation queue
    @Test
    @Transactional
    void shouldSetBookAvailableWhenNoReservationsExist() {

        book.setAvailable(false);
        bookRepo.save(book);

        service.processReservationQueue(book);

        Book updated = bookRepo.findById(book.getIsbn()).orElseThrow();
        assertThat(updated.isAvailable()).isTrue();
    }

    // Process reservation queue, success
    @Test
    @Transactional
    void shouldProcessReservationQueueAndCreateLoan() {

        // Set reservations
        Reservation r1 = new Reservation(user, book);
        Reservation r2 = new Reservation(user, book);

        reservationRepo.save(r1);
        reservationRepo.save(r2);

        book.setAvailable(false);
        bookRepo.save(book);

        // Process queue
        service.processReservationQueue(book);

        // Set status not_active
        List<Reservation> all = reservationRepo.findByBookIsbnAndStatus(book.getIsbn(), Reservation.Status.not_active);

        assertThat(all).isNotEmpty();

        // Loan
        List<Loan> loans = loanRepo.findByUserUserIdAndReturnDate(user.getUserId(), null);
        assertThat(loans).hasSize(1);

        // Book is not available
        Book updated = bookRepo.findById(book.getIsbn()).orElseThrow();
        assertThat(updated.isAvailable()).isFalse();
    }

    // Localization
    @Test
    @Transactional
    void shouldLocalizeReservations() {

        Genre genre = new Genre();
        genre.setGenre("fantasy");
        genreRepo.save(genre);

        Language language = new Language();
        language.setLanguage("english");
        languageRepo.save(language);

        service.createReservation(user.getUserId(), book.getIsbn());

        List<Reservation> reservations =
                reservationRepo.findByUserUserId(user.getUserId());

        List<ReservationDTO> result =
                service.localizeReservations(reservations, "en-US");

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isNotNull();
    }



}
