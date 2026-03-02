package com.library.backend.reservation;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static com.library.backend.reservation.Reservation.Status.active;
import static com.library.backend.reservation.Reservation.Status.not_active;

@Service
public class ReservationService {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final ReservationRepository reservationRepo;

    public ReservationService(UserRepository userRepo, BookRepository bookRepo, ReservationRepository reservationRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.reservationRepo = reservationRepo;
    }

    // Get all reservations
    public List<ReservationDTO> getAllReservations() {
        Iterable<Reservation> reservations = reservationRepo.findAll();

        // Convert Iterable (from CrudRepository) to stream to remove unnecessary data (e.g. hibernate lazy initializer)
        return StreamSupport.stream(reservations.spliterator(), false)
                .map(ReservationDTO::new)
                .toList();
    }

    // Get reservation by id
    public ReservationDTO getReservationById(Integer reservationId) {
        return reservationRepo.findById(reservationId)
                .map(ReservationDTO::new)
                .orElse(null);
    }

    // Get reservations by user
    public List<ReservationDTO> getReservationsByUser(Integer userId) {
        return reservationRepo.findByUserUserId(userId)
                .stream()
                .filter(reservation -> reservation.getStatus() == active)
                .map(ReservationDTO::new)
                .toList();
    }

    // Get reservations by books isbn
    public List<ReservationDTO> getReservationsByIsbn(String isbn) {
        return reservationRepo.findByBookIsbn(isbn)
                .stream()
                .map(ReservationDTO::new)
                .toList();
    }

    // Create new reservation
    @Transactional
    public ReservationDTO createReservation(CreateReservationDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("user not found: "));
        Book book = bookRepo.findById(dto.getIsbn()).orElseThrow(() -> new RuntimeException("isbn not found: "));

        Reservation reservation = new Reservation(user, book);
        reservationRepo.save(reservation);

        return new ReservationDTO(reservation);

    }

    // Cancel reservation
    @Transactional
    public void cancelReservation(CancelReservationDTO dto) {
        Book book = bookRepo.findById(dto.getIsbn()).orElseThrow(() -> new RuntimeException("isbn not found: "));
        Reservation reservation  = reservationRepo.findById(dto.getReservationId()).orElseThrow(() -> new RuntimeException("reservation not found: "));

        reservation.setStatus(not_active);
        reservationRepo.save(reservation);
    }
}
