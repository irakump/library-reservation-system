package com.library.backend.reservation;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
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

import java.util.List;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(ReservationService.class)
public class ReservationServiceTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private ReservationService service;

    @MockitoBean
    private NotificationService notificationService;

    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = userRepo.save(new User("user@user.com", "User #3", "S0meHash3dP4$$.1831!Dds"));
        book = bookRepo.save(new Book("12345670", "Test book", 2026, "This is test book", "fantasy", "english", true));
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
}
