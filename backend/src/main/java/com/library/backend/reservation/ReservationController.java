package com.library.backend.reservation;

import com.library.backend.security.AuthorizationUtil;
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

    // Get all reservations -Admin only
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations(HttpServletRequest request) {
        AuthorizationUtil.checkAdminAccess(request);
        List<ReservationDTO> reservations = reservationService.getAllReservations();

        return ResponseEntity.ok(reservations);
    }

    // Get reservation by id
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer reservationId) {
        ReservationDTO reservation = reservationService.getReservationById(reservationId);

        return ResponseEntity.ok(reservation);
    }

    // Get active reservations by user's id - own data or admin
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getActiveReservationsByUser(@PathVariable Integer userId, HttpServletRequest request) {
        AuthorizationUtil.checkUserAccess(request, userId);
        List<ReservationDTO> reservations = reservationService.getActiveReservationsByUser(userId);

        return ResponseEntity.ok(reservations);
    }

    // Get reservations by books isbn
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByIsbn(@PathVariable String isbn) {
        List<ReservationDTO> reservations = reservationService.getActiveReservationsByIsbn(isbn, Reservation.Status.active);

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

    // Get reservation queue length
    @GetMapping("/book/{isbn}/queue-length")
    public ResponseEntity<Map<String, Integer>> getQueueLength(@PathVariable String isbn) {
        int length = reservationService.getActiveReservationsByIsbn(isbn, Reservation.Status.active).size();
        return ResponseEntity.ok(Map.of("queueLength", length));
    }

}

