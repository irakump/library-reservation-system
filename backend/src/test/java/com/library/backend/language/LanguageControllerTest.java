package com.library.backend.language;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(
        value = LanguageController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
public class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LanguageRepository repository;

    @Test
    public void testGetAllLanguages() throws Exception {
        Language english = new Language("English");
        Language finnish = new Language("Finnish");
        when(repository.findAll()).thenReturn(Arrays.asList(english, finnish));

        mockMvc.perform(get("/api/language"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].language").value("English"))
                .andExpect(jsonPath("$[1].language").value("Finnish"));
    }

    @Test
    public void testGetLanguageByName() throws Exception {
        Language english = new Language("English");
        when(repository.findById("English")).thenReturn(Optional.of(english));

        mockMvc.perform(get("/api/language/English"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.language").value("English"));
    }

    @Test
    public void testGetLanguageByName_NotFound() throws Exception {
        when(repository.findById("Swedish")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/language/Swedish"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
