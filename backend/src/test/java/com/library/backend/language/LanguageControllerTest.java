package com.library.backend.language;
/*
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
        Language english = new Language("english", "英語", "الإنجليزية");
        Language finnish = new Language("finnish", "フィンランド語", "الفنلندية");
        when(repository.findAll()).thenReturn(Arrays.asList(english, finnish));

        mockMvc.perform(get("/api/language"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].language").value("english"))
                .andExpect(jsonPath("$[0].languageJa").value("英語"))
                .andExpect(jsonPath("$[0].languageAr").value("الإنجليزية"))
                .andExpect(jsonPath("$[1].language").value("finnish"))
                .andExpect(jsonPath("$[1].languageJa").value("フィンランド語"))
                .andExpect(jsonPath("$[1].languageAr").value("الفنلندية"));
    }

    @Test
    public void testGetLanguageByName() throws Exception {
        Language english = new Language("english", "英語", "الإنجليزية");
        when(repository.findById("english")).thenReturn(Optional.of(english));

        mockMvc.perform(get("/api/language/english"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.language").value("english"))
                .andExpect(jsonPath("$.languageJa").value("英語"))
                .andExpect(jsonPath("$.languageAr").value("الإنجليزية"));
    }

    @Test
    public void testGetLanguageByName_NotFound() throws Exception {
        when(repository.findById("swedish")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/language/swedish"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}


 */



