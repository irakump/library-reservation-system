package com.library.backend.genre;

import com.library.backend.security.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = GenreController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GenreRepository repository;

    @Test
    void testGetAllGenresEnglish() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Genre("fantasy", "ファンタジー", "فانتازيا"),
                new Genre("history", "歴史", "تاريخ")
        ));

        mockMvc.perform(get("/api/genre/all/en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].genre").value("fantasy"))
                .andExpect(jsonPath("$[0].genreKey").value("fantasy"))
                .andExpect(jsonPath("$[1].genre").value("history"))
                .andExpect(jsonPath("$[1].genreKey").value("history"));
    }

    @Test
    void testGetGenreByNameEnglish() throws Exception {
        when(repository.findById("fantasy")).thenReturn(Optional.of(new Genre("fantasy", "ファンタジー", "فانتازيا")));

        mockMvc.perform(get("/api/genre/fantasy/en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genre").value("fantasy"))
                .andExpect(jsonPath("$.genreKey").value("fantasy"));
    }

}
