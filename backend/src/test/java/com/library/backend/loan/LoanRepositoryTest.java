package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class LoanRepositoryTest {
    @Autowired
    LoanRepository repository;

    @Autowired
    TestEntityManager em;

    // Create operation
    @Test
    void shouldCreateNewLoan() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", 2026, "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);

        Loan loan = new Loan( dueDate, user, book); // Due date 2 weeks

        Loan insertedLoan = repository.save(loan);
        em.flush();
        em.clear();

        Loan foundLoan = em.find(Loan.class, insertedLoan.getLoanId());

        assertThat(insertedLoan).isNotNull();
        assertEquals(user.getUserId(), foundLoan.getUser().getUserId());
        assertEquals(book.getIsbn(), foundLoan.getBook().getIsbn());
        assertEquals(loan.getLoanId(), foundLoan.getLoanId());
    }

    // Update operation
    @Test
    void shouldUpdateReturnDate() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", 2026, "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);
        Loan loan = new Loan(dueDate, user, book);

        repository.save(loan);
        em.flush();
        em.clear();

        Timestamp returnDate = Timestamp.valueOf("2026-02-11 00:00:00");
        loan.setReturnDate(returnDate);
        repository.save(loan);

        Loan updatedLoan = em.find(Loan.class, loan.getLoanId());

        assertThat(updatedLoan).isNotNull();
        assertEquals(returnDate, updatedLoan.getReturnDate());
    }

    // Test find loan by id
    @Test
    void shouldFindLoanById() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", 2026, "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);

        Loan loan = new Loan(dueDate, user, book); // Due date 2 weeks

        Loan insertedLoan = repository.save(loan);
        em.flush();
        em.clear();

        Optional<Loan> fetchedLoan = repository.findById(loan.getLoanId());
        assertTrue(fetchedLoan.isPresent());
        assertEquals(insertedLoan.getLoanId(), fetchedLoan.get().getLoanId());
    }

    // Empty loan
    @Test
    void shouldNotFindNonExistingLoan() {
        Optional<Loan> foundLoan = repository.findById(9999);
        assertTrue(foundLoan.isEmpty());
    }

    // Delete operation
    @Test
    void shouldDeleteLoan() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", 2026, "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);
        Loan loan = new Loan(dueDate, user, book); // Due date 2 weeks

        repository.save(loan);
        repository.delete(loan);
        em.flush();
        assertThat(em.find(Loan.class, loan.getLoanId())).isNull();

    }
}
