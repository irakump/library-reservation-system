package com.library.backend.reservation;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReservationDTOTest {

    @Test
    void shouldMapReservationCorrectly() {
        User user = new User("a@a.com", "User", "pw");
        Book book = new Book("123", "t", "t", "t", 2020, "d", "d", "d", "genre", "lang", true);

        Reservation r = new Reservation(user, book);

        ReservationDTO dto = new ReservationDTO(r);

        assertThat(dto.getUserId()).isEqualTo(user.getUserId());
        assertThat(dto.getIsbn()).isEqualTo("123");
        assertThat(dto.getTitle()).isEqualTo("t");
    }
}
