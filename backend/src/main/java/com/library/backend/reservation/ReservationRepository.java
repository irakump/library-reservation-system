package com.library.backend.reservation;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    List<Reservation> findByUserUserId(Integer userId);
    List<Reservation> findByBookIsbnAndStatus(String isbn, Reservation.Status status);
}
