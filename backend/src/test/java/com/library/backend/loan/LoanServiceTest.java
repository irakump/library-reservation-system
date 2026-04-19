package com.library.backend.loan;
import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.genre.Genre;
import com.library.backend.genre.GenreRepository;
import com.library.backend.language.Language;
import com.library.backend.language.LanguageRepository;
import com.library.backend.notifications.NotificationService;
import com.library.backend.reservation.ReservationService;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private BookRepository bookRepo;

    @Mock
    private LoanRepository loanRepo;

    @Mock
    private GenreRepository genreRepo;

    @Mock
    private LanguageRepository languageRepo;

    @Mock
    private ReservationService reservationService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private LoanService loanService;

    private User user;
    private Book book;
    private Loan loan;
    private Loan returnedLoan;
    private Loan upcomingLoan;
    private Genre genre;
    private Language language;
    private LocalDate advancedDate;

    @BeforeEach
    void setUp() {
        user = new User("joku@gmail.com", "joku", "LKJKJS9987dakkcvdQl");

        book = new Book("88281201228", "joku", "joku in japan", "joku", 2005, "jshdahdhad", "jsjdd", "shddd", "children", "finnish", false);

        Book returnedBook = new Book("11111", "Returned", "Japanese title", "Arabese title", 2833, "something", "something", "hsdksd", "skdks", "finnish", false );

        loan = new Loan(LocalDate.now().plusWeeks(2), user, book);

        advancedDate = LocalDate.now().plusDays(2);
        returnedLoan = new Loan(LocalDate.now().minusDays(3), user, returnedBook);
        returnedLoan.setReturnDate(LocalDateTime.now().minusDays(10));

        upcomingLoan = new Loan(advancedDate, user, book);

        genre = new Genre("children", "子供たち", "أطفال");
        language = new Language("finnish", "フィンランド語", "الفنلندية");
    }


    //getAllLoans
    @Test
    void getAllLoans() {
        when(loanRepo.findAll()).thenReturn(List.of(loan, returnedLoan, upcomingLoan));

        List<LoanDTO> result = loanService.getAllLoans();

        assertEquals(3, result.size());
        verify(loanRepo, times(1)).findAll();
    }

    //getLoansByUser()
    @Test
    void getActiveLoansByUser() {
        when(loanRepo.findByUserUserId(1)).thenReturn(List.of(loan, returnedLoan, upcomingLoan));

        List<Loan> result = loanService.getActiveLoansByUser(1);

        assertThat(result).containsExactly(loan, upcomingLoan);
    }


    //getLoanHistory()
    @Test
    void testLoanHistoryByUser() {
        upcomingLoan.setReturnDate(LocalDateTime.now());
        when(loanRepo.findByUserUserId(1)).thenReturn(List.of(loan, upcomingLoan, returnedLoan));

        List<Loan> result = loanService.getLoanHistoryByUser(1);

        assertThat(result).containsExactly(upcomingLoan, returnedLoan);
    }

    @Test
    void testLocalizeLoansEnglish() {
        when(genreRepo.findById("children")).thenReturn(Optional.of(genre));
        when(languageRepo.findById("finnish")).thenReturn(Optional.of(language));

        List<LoanDTO> result = loanService.localizeLoans(List.of(loan), "en");

        assertEquals(1, result.size());
        assertEquals("joku", result.getFirst().getTitle());
        assertEquals("jshdahdhad", result.getFirst().getDescription());
        assertEquals("children", result.getFirst().getGenre());
        assertEquals("finnish", result.getFirst().getLanguage());
    }

    @Test
    void testLocalizeLoansJapanese() {
        when(genreRepo.findById("children")).thenReturn(Optional.of(genre));
        when(languageRepo.findById("finnish")).thenReturn(Optional.of(language));

        List<LoanDTO> result = loanService.localizeLoans(List.of(loan), "ja-JP");

        assertEquals(1, result.size());
        assertEquals("joku in japan", result.getFirst().getTitle());
        assertEquals("jsjdd", result.getFirst().getDescription());
        assertEquals("子供たち", result.getFirst().getGenre());
        assertEquals("フィンランド語", result.getFirst().getLanguage());
    }

    @Test
    void testLocalizeLoansArabic() {
        when(genreRepo.findById("children")).thenReturn(Optional.of(genre));
        when(languageRepo.findById("finnish")).thenReturn(Optional.of(language));

        List<LoanDTO> result = loanService.localizeLoans(List.of(loan), "ar-u-nu-arab");

        assertEquals(1, result.size());
        assertEquals("joku", result.getFirst().getTitle());
        assertEquals("shddd", result.getFirst().getDescription());
        assertEquals("أطفال", result.getFirst().getGenre());
        assertEquals("الفنلندية", result.getFirst().getLanguage());
    }


    // create loan
    @Test
    void testCreateLoanSetUnavailable() {
        CreateLoanDTO dto = new CreateLoanDTO(1, "123");

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(bookRepo.findById("123")).thenReturn(Optional.of(book));
        when(loanRepo.save(any(Loan.class))).thenReturn(loan);

        LoanDTO result = loanService.createLoan(dto, "en-US");

        assertNotNull(result);
        assertFalse(book.isAvailable());

        verify(loanRepo).save(any(Loan.class));
        verify(bookRepo).save(book);
    }

    @Test
    void testCreateUserNotFound() {
        CreateLoanDTO dto = new CreateLoanDTO(1, "123");

        when(userRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> loanService.createLoan(dto, "en-US"));
    }


    //returnLoan

    @Test
    void testReturnLoan() {
        ReturnLoanDTO dto = new ReturnLoanDTO(10, 1, "88281201228");
        when(bookRepo.findById("88281201228")).thenReturn(Optional.of(book));
        when(loanRepo.findById(10)).thenReturn(Optional.of(loan));

        loanService.returnLoan(dto);

        assertThat(loan.getReturnDate()).isNotNull();
        verify(loanRepo).save(loan);
        verify(bookRepo).save(book);
        verify(reservationService).processReservationQueue(book, loan);
        verify(notificationService).notifyDueDate(user, book);
    }

    @Test
    void testReturnNotFound() {
        ReturnLoanDTO dto = new ReturnLoanDTO(30, 1,"123");
        when(bookRepo.findById("123")).thenReturn(Optional.of(book));

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> loanService.returnLoan(dto));
        assertEquals("loan not found", exception.getMessage());

    }

    //returnAllBooksByDueDate

    @Test
    void testProcessAllDueLoans() {
        Loan loan1 = new Loan(LocalDate.now(), user, book);
        Loan loan2 = new Loan(LocalDate.now(), user, book);
        when(loanRepo.findByDueDate(LocalDate.now())).thenReturn(List.of(loan1, loan2));

        loanService.returnAllBooksByDueDate();

        assertThat(loan1.getReturnDate()).isNotNull();
        assertThat(loan2.getReturnDate()).isNotNull();
        verify(notificationService, times(2)).notifyDueDate(user, book);
        verify(reservationService, times(2)).processReservationQueue(eq(book), any(Loan.class));
    }


    //shouldNotifyComingDueDate

    @Test
    void testNotifyForDueDateInTwoDays() {
        when(loanRepo.findByDueDate(advancedDate)).thenReturn(List.of(upcomingLoan));

        loanService.checkAllComingDueDate();

        verify(notificationService).notifyComingUpDueDate(user, book);
    }
}
