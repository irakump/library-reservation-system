package com.library.backend.reservation;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Test database and JPA

@DataJpaTest
public class ReservationRepositoryTest {
    @Autowired
    ReservationRepository repository;

    @Autowired
    TestEntityManager em;

    // Update operation
    @Test
    void shouldCreateNewReservation() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", "Test book", "Test book", 2026, "This is test book", "This is test book", "This is test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        Reservation reservation = new Reservation(user, book);

        Reservation insertedReservation = repository.save(reservation);
        em.flush();
        em.clear();

        Reservation foundReservation = em.find(Reservation.class, insertedReservation.getReservationId());

        assertThat(insertedReservation).isNotNull();
        assertEquals(user.getUserId(), foundReservation.getUser().getUserId());
        assertEquals(book.getIsbn(), foundReservation.getBook().getIsbn());
        assertEquals(reservation.getReservationId(), foundReservation.getReservationId());
    }

    // Test find reservation by id
    @Test
    void shouldFindReservationById() {
        User user = new User("test@email.com", "TestUser", "h8d6s6Gj!230Kh");
        Book book = new Book("12345670", "Test book", "Test book", "Test book", 2026, "This is test book", "Test book", "Test book", "Children", "english", true);

        em.persist(user);
        em.persist(book);

        Reservation reservation = new Reservation(user, book);

        Reservation insertedReservation = repository.save(reservation);
        em.flush();
        em.clear();

        Optional<Reservation> foundReservation = repository.findById(insertedReservation.getReservationId());

        assertTrue(foundReservation.isPresent());
        assertEquals(insertedReservation.getReservationId(), foundReservation.get().getReservationId());
    }

    // Empty reservation
    @Test
    void shouldNotFindNonExistingReservation() {
        Optional<Reservation> foundReservation = repository.findById(9999);
        assertTrue(foundReservation.isEmpty());
    }

}
