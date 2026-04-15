package com.library.backend.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreateReservationDTOTest {

    @Test
    void shouldStoreValues() {
        CreateReservationDTO dto = new CreateReservationDTO();

        ReflectionTestUtils.setField(dto, "userId", 10);
        ReflectionTestUtils.setField(dto, "isbn", "123");

        assertThat(dto.getUserId()).isEqualTo(10);
        assertThat(dto.getIsbn()).isEqualTo("123");
    }
}