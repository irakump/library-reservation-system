package com.library.backend.loan;
import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.genre.Genre;
import com.library.backend.genre.GenreRepository;
import com.library.backend.language.Language;
import com.library.backend.language.LanguageRepository;
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

    @InjectMocks
    private LoanService loanService;

    private User user;
    private Book book;
    private Loan loan;
    private Genre genre;
    private Language language;

    @BeforeEach
    void setUp() {
        user = new User("joku@gmail.com", "joju", "LKJKJS9987dakkcvdQl");

        book = new Book("88281201228", "joku", "joku in japan", "joku", 2005, "jshdahdhad", "jsjdd", "shddd", "children", "finnish", true);
        book.setAvailable(true);

        loan = new Loan(LocalDate.now().plusWeeks(2), user, book);

        genre = new Genre("children", "子供たち", "أطفال");
        language = new Language("finnish", "フィンランド語", "الفنلندية");
    }


    @Test
    void getAllLoans() {
        when(loanRepo.findAll()).thenReturn(List.of(loan));

        List<LoanDTO> result = loanService.getAllLoans();

        assertEquals(1, result.size());
        verify(loanRepo, times(1)).findAll();
    }

    //getLoansByUser()
    @Test
    void getActiveLoansByUser() {
        when(loanRepo.findByUserUserId(1)).thenReturn(List.of(loan));

        List<Loan> result = loanService.getActiveLoansByUser(1);

        assertEquals(1, result.size());
        verify(loanRepo).findByUserUserId(1);
    }


    //getLoanHistory()
    @Test
    void testLoanHistoryByUser() {
        loan.setReturnDate(LocalDateTime.now());
        when(loanRepo.findByUserUserId(1)).thenReturn(List.of(loan));

        List<Loan> result = loanService.getLoanHistoryByUser(1);

        assertEquals(1, result.size());
        verify(loanRepo).findByUserUserId(1);
    }

    @Test
    void testLocalizeLoansEnglish() {
        when(genreRepo.findById("children")).thenReturn(Optional.of(genre));
        when(languageRepo.findById("finnish")).thenReturn(Optional.of(language));

        List<LoanDTO> result = loanService.localizeLoans(List.of(loan), "en");

        assertEquals(1, result.size());
        assertEquals("joku", result.get(0).getTitle());
        assertEquals("jshdahdhad", result.get(0).getDescription());
        assertEquals("children", result.get(0).getGenre());
        assertEquals("finnish", result.get(0).getLanguage());
    }

    @Test
    void testLocalizeLoansJapanese() {
        when(genreRepo.findById("children")).thenReturn(Optional.of(genre));
        when(languageRepo.findById("finnish")).thenReturn(Optional.of(language));

        List<LoanDTO> result = loanService.localizeLoans(List.of(loan), "ja-JP");

        assertEquals(1, result.size());
        assertEquals("joku in japan", result.get(0).getTitle());
        assertEquals("jsjdd", result.get(0).getDescription());
        assertEquals("子供たち", result.get(0).getGenre());
        assertEquals("フィンランド語", result.get(0).getLanguage());
    }

    @Test
    void testLocalizeLoansArabic() {
        when(genreRepo.findById("children")).thenReturn(Optional.of(genre));
        when(languageRepo.findById("finnish")).thenReturn(Optional.of(language));

        List<LoanDTO> result = loanService.localizeLoans(List.of(loan), "ar-u-nu-arab");

        assertEquals(1, result.size());
        assertEquals("joku", result.get(0).getTitle());
        assertEquals("shddd", result.get(0).getDescription());
        assertEquals("أطفال", result.get(0).getGenre());
        assertEquals("الفنلندية", result.get(0).getLanguage());
    }


    //craete loan
    @Test
    void testCreateLoanSetUnavailable() {
        CreateLoanDTO dto = new CreateLoanDTO(1, "123");

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(bookRepo.findById("123")).thenReturn(Optional.of(book));
        when(loanRepo.save(any(Loan.class))).thenReturn(loan);

        LoanDTO result = loanService.createLoan(dto);

        assertNotNull(result);
        assertFalse(book.getAvailability());

        verify(loanRepo).save(any(Loan.class));
        verify(bookRepo).save(book);
    }

    @Test
    void testCreateUserNotFound() {
        CreateLoanDTO dto = new CreateLoanDTO(1, "123");

        when(userRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> loanService.createLoan(dto));
    }

    //return loans
    //@Test
    // void testReturnLoan() {
    //     ReturnLoanDTO dto = new ReturnLoanDTO(1, 1, "123");

    //     when(bookRepo.findById("123")).thenReturn(Optional.of(book));
    //     when(loanRepo.findById(1)).thenReturn(Optional.of(loan));
    //     when(userRepo.findById(1)).thenReturn(Optional.of(user));

    //     loanService.returnLoan(dto);

    //     assertNotNull(loan.getReturnDate());
    //     assertTrue(book.getAvailability());//

    //     verify(loanRepo).save(loan);
    //     verify(bookRepo).save(book);
    // }

    @Test
    void testReturnNotFound() {
        ReturnLoanDTO dto = new ReturnLoanDTO(30, 1,"123");
        when(bookRepo.findById("123")).thenReturn(Optional.of(book));

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> loanService.returnLoan(dto));
        assertEquals("loan not found", exception.getMessage());

    }
}
