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
    public void testGetAllLanguagesEnglish() throws Exception {
        Language english = new Language("english", "英語", "الإنجليزية");
        Language finnish = new Language("finnish", "フィンランド語", "الفنلندية");
        when(repository.findAll()).thenReturn(Arrays.asList(english, finnish));

        mockMvc.perform(get("/api/language/all/en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].language").value("english"))
                .andExpect(jsonPath("$[0].languageKey").value("english"))
                .andExpect(jsonPath("$[1].language").value("finnish"))
                .andExpect(jsonPath("$[1].languageKey").value("finnish"));
    }

    @Test
    public void testGetAllLanguagesJapanese() throws Exception {
        Language english = new Language("english", "英語", "الإنجليزية");
        Language finnish = new Language("finnish", "フィンランド語", "الفنلندية");
        when(repository.findAll()).thenReturn(Arrays.asList(english, finnish));

        mockMvc.perform(get("/api/language/all/ja-JP"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].language").value("英語"))
                .andExpect(jsonPath("$[0].languageKey").value("english"))
                .andExpect(jsonPath("$[1].language").value("フィンランド語"))
                .andExpect(jsonPath("$[1].languageKey").value("finnish"));
    }

    @Test
    public void testGetAllLanguagesArabic() throws Exception {
        Language english = new Language("english", "英語", "الإنجليزية");
        Language finnish = new Language("finnish", "フィンランド語", "الفنلندية");
        when(repository.findAll()).thenReturn(Arrays.asList(english, finnish));

        mockMvc.perform(get("/api/language/all/ar-u-nu-arab"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].language").value("الإنجليزية"))
                .andExpect(jsonPath("$[0].languageKey").value("english"))
                .andExpect(jsonPath("$[1].language").value("الفنلندية"))
                .andExpect(jsonPath("$[1].languageKey").value("finnish"));
    }

    @Test
    public void testGetLanguageByNameEnglish() throws Exception {
        Language english = new Language("english", "英語", "الإنجليزية");
        when(repository.findById("english")).thenReturn(Optional.of(english));

        mockMvc.perform(get("/api/language/english/en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.language").value("english"))
                .andExpect(jsonPath("$.languageKey").value("english"));
    }

    @Test
    public void testGetLanguageByNameJapanese() throws Exception {
        Language english = new Language("english", "英語", "الإنجليزية");
        when(repository.findById("english")).thenReturn(Optional.of(english));

        mockMvc.perform(get("/api/language/english/ja-JP"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.language").value("英語"))
                .andExpect(jsonPath("$.languageKey").value("english"));
    }

    @Test
    public void testGetLanguageByName_NotFound() throws Exception {
        when(repository.findById("swedish")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/language/swedish/en"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}



