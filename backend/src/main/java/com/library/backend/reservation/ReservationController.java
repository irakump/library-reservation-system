package com.library.backend.reservation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ReservationDTO>> getActiveReservationsByUser(@PathVariable Integer userId) {
        List<ReservationDTO> reservations = reservationService.getActiveReservationsByUser(userId);

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
    public ResponseEntity<?> createReservation(HttpServletRequest request, @RequestBody CreateReservationDTO dto) {
        int userIdFromJwt = (int) request.getAttribute("userId");

        try {
            ReservationDTO createdReservation = reservationService.createReservation(userIdFromJwt, dto.getIsbn());
            return ResponseEntity.ok(createdReservation);
        } catch (RuntimeException e) {
            // Return error message as JSON for UI
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }


    // Change reservation (cancel)
    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelReservation(HttpServletRequest request, @RequestBody CancelReservationDTO dto) {

        int userIdFromJwt = (int) request.getAttribute("userId");
        reservationService.cancelReservation(dto.getReservationId(), userIdFromJwt);
        return ResponseEntity.ok().build();
    }
}

