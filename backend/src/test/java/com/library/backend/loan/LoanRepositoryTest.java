package com.library.backend.loan;

import com.library.backend.book.Book;
import com.library.backend.reservation.Reservation;
import com.library.backend.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class LoanRepositoryTest {
    @Autowired
    LoanRepository repository;

    @Autowired
    TestEntityManager em;

    // Update operation
    @Test
    void shouldCreateNewLoan() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", 2026, "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        Loan loan = new Loan(new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 14 * 24 * 3600 * 1000), null, user, book); // Due date 2 weeks

        Loan insertedLoan = repository.save(loan);
        em.flush();
        em.clear();

        Loan foundLoan = em.find(Loan.class, insertedLoan.getLoanId());

        assertThat(insertedLoan).isNotNull();
        assertEquals(user.getUserId(), foundLoan.getUser().getUserId());
        assertEquals(book.getIsbn(), foundLoan.getBook().getIsbn());
        assertEquals(loan.getLoanId(), foundLoan.getLoanId());
    }

    // Test find loan by id
    @Test
    void shouldFindLoanById() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", 2026, "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        Loan loan = new Loan(new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 14 * 24 * 3600 * 1000), null, user, book); // Due date 2 weeks

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

}
