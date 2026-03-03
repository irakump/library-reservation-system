package com.library.backend.reservation;

import com.library.backend.book.Book;
import com.library.backend.book.BookRepository;
import com.library.backend.loan.Loan;
import com.library.backend.loan.LoanRepository;
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
    private final LoanRepository loanRepo;

    public ReservationService(UserRepository userRepo, BookRepository bookRepo, ReservationRepository reservationRepo, LoanRepository loanRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.reservationRepo = reservationRepo;
        this.loanRepo = loanRepo;
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

    // Get active reservations by user
    public List<ReservationDTO> getActiveReservationsByUser(Integer userId) {
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
    public ReservationDTO createReservation(int userId, String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found: "));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found: "));

        // Check existing active reservation
        List<ReservationDTO> reservations = getActiveReservationsByUser(userId);
        for (ReservationDTO r : reservations) {
            if (r.getBookIsbn().equals(isbn)) {
                throw new RuntimeException("Book is currently reserved by this user");
            }
        }

        // Check existing loan
        List<Loan> activeLoans = loanRepo.findByUserUserIdAndReturnDate(userId, null);
        boolean hasLoaned = activeLoans.stream()
                .anyMatch(loan -> loan.getBook().getIsbn().equals(isbn));

        if (hasLoaned) {
            throw new RuntimeException("Book is currently loaned by this user, cannot reserve it");
        }

        // New reservation
        Reservation reservation = new Reservation(user, book);
        reservationRepo.save(reservation);

        return new ReservationDTO(reservation);

    }

    // Cancel reservation
    @Transactional
    public void cancelReservation(int reservationId, int userId) {
        Reservation reservation  = reservationRepo.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found: "));

        if (reservation.getUser().getUserId() != userId) {
            throw new RuntimeException("Cannot cancel another user's reservation");
        }

        reservation.setStatus(not_active);
        reservationRepo.save(reservation);
    }
}
