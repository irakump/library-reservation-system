package com.library.backend.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService service) {
        this.reservationService = service;
    }

    // Get all reservations
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();

        return ResponseEntity.ok(reservations);
    }

    // Get reservation by id
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer reservationId) {
        ReservationDTO reservation = reservationService.getReservationById(reservationId);

        return ResponseEntity.ok(reservation);
    }

    // Get active reservations by user's id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@PathVariable Integer userId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByUser(userId);

        return ResponseEntity.ok(reservations);
    }

    // Get reservations by books isbn
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByIsbn(@PathVariable String isbn) {
        List<ReservationDTO> reservations = reservationService.getReservationsByIsbn(isbn);

        return ResponseEntity.ok(reservations);
    }

    // Create new reservation
    @PostMapping("/new")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody CreateReservationDTO request) {
        ReservationDTO createdReservation = reservationService.createReservation(request);
        return ResponseEntity.ok(createdReservation);
    }


    // Change reservation (cancel)
    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelReservation(@RequestBody CancelReservationDTO request) {
        reservationService.cancelReservation(request);
        return ResponseEntity.ok().build();
    }
}

