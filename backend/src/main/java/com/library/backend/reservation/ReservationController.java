package com.library.backend.reservation;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationRepository repository;

    public ReservationController(ReservationRepository repository) {
        this.repository = repository;
    }

    // Get all reservations
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        Iterable<Reservation> reservations = repository.findAll();
        return StreamSupport.stream(reservations.spliterator(), false)  // Convert Iterable (from CrudRepository) to stream
                .map(ReservationDTO::new)
                .toList();
    }

    // Get reservation by id
    @GetMapping("/{reservationId}")
    public ReservationDTO getReservationById(@PathVariable Integer reservationId) {
        return repository.findById(reservationId)
                .map(ReservationDTO::new)
                .orElse(null);
    }

    // Get reservations by user's id
    @GetMapping("/user/{userId}")
    public List<ReservationDTO> getReservationsByUserId(@PathVariable Integer userId) {
        return repository.findByUserUserId(userId)
                .stream()
                .map(ReservationDTO::new)
                .toList();
    }

    // Get reservations by books isbn
    @GetMapping("/book/{isbn}")
    public List<ReservationDTO> getReservationsByIsbn(@PathVariable String isbn) {
        return repository.findByBookIsbn(isbn)
                .stream()
                .map(ReservationDTO::new)
                .toList();
    }
}

