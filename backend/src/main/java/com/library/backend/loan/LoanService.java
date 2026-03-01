package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LoanService {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final LoanRepository loanRepo;

    public LoanService(UserRepository userRepo, BookRepository bookRepo, LoanRepository loanRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.loanRepo = loanRepo;
    }

    @Transactional
    public LoanDTO createLoan(CreateLoanDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("user not found: "));
        Book book = bookRepo.findById(dto.getIsbn()).orElseThrow(() -> new RuntimeException("isbn not found: "));

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);
        Loan loan = new Loan(dueDate, user, book);
        loanRepo.save(loan);

        book.setAvailable(false);
        bookRepo.save(book);

        return new LoanDTO(loan);
    }

    @Transactional
    public void returnLoan(ReturnLoanDTO dto) {
        Book book = bookRepo.findById(dto.getIsbn()).orElseThrow(() -> new RuntimeException("book not found"));
        Loan loan = loanRepo.findById(dto.getLoanId()).orElseThrow(() -> new RuntimeException("loan not found"));

        LocalDateTime returnDate = LocalDateTime.now();
        loan.setReturnDate(returnDate);
        loanRepo.save(loan);

        book.setAvailable(true);
        bookRepo.save(book);
    }
}
