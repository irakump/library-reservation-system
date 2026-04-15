package com.library.backend.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CancelReservationDTOTest {

    @Test
    void shouldStoreValues() {
        CancelReservationDTO dto = new CancelReservationDTO();

        ReflectionTestUtils.setField(dto, "reservationId", 5);
        ReflectionTestUtils.setField(dto, "userId", 10);
        ReflectionTestUtils.setField(dto, "isbn", "123");

        assertThat(dto.getReservationId()).isEqualTo(5);
        assertThat(dto.getUserId()).isEqualTo(10);
        assertThat(dto.getIsbn()).isEqualTo("123");
    }
}
