package com.library.backend.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.backend.book.Book;
import com.library.backend.security.JwtAuthenticationFilter;
import com.library.backend.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        value = ReservationController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class
        )
)

@AutoConfigureMockMvc(addFilters = false)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReservationService reservationService;


    // Get reservation by id
    @Test
    void shouldReturnReservationById() throws Exception {

        ReservationDTO dto = new ReservationDTOBuilder().build();

        when(reservationService.getReservationById(1))
                .thenReturn(dto);

        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationId").value(1));
    }

    // Get user's reservations
    @Test
    void shouldReturnUserReservations() throws Exception {

        ReservationDTO dto = new ReservationDTOBuilder().build();

        List<Reservation> reservations = List.of(new Reservation());

        when(reservationService.getActiveReservationsByUser(1))
                .thenReturn(reservations);

        when(reservationService.localizeReservations(reservations, "en-US"))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/reservations/user/1/en-US")
                        .requestAttr("userId", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reservationId").value(1));

        verify(reservationService).getActiveReservationsByUser(1);
        verify(reservationService).localizeReservations(reservations, "en-US");
    }

    // Get ISBN
    @Test
    void shouldReturnReservationsByIsbn() throws Exception {

        ReservationDTO dto = new ReservationDTOBuilder().build();

        when(reservationService.getActiveReservationsByIsbn(
                eq("123"),
                eq(Reservation.Status.active)
        )).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/reservations/book/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("123"));

        verify(reservationService, times(1))
                .getActiveReservationsByIsbn("123", Reservation.Status.active);
    }


    // Helper builder to create mock ReservationDTO
    static class ReservationDTOBuilder {
        ReservationDTO build() {
            Reservation reservation = org.mockito.Mockito.mock(Reservation.class);

            Book book = org.mockito.Mockito.mock(Book.class);

            User user = org.mockito.Mockito.mock(User.class);

            when(reservation.getReservationId()).thenReturn(1);
            when(reservation.getStatus()).thenReturn(Reservation.Status.active);

            when(user.getUserId()).thenReturn(1);
            when(reservation.getUser()).thenReturn(user);

            when(book.getIsbn()).thenReturn("123");
            when(book.getTitle()).thenReturn("Test");
            when(book.getDescription()).thenReturn("Description");
            when(book.getYear()).thenReturn(2026);
            when(book.getLanguage()).thenReturn("en-US");
            when(book.getGenre()).thenReturn("fantasy");
            when(book.getAuthors()).thenReturn(List.of());

            when(reservation.getBook()).thenReturn(book);

            return new ReservationDTO(reservation);
        }
    }
}