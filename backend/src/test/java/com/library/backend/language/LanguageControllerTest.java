package com.library.backend.language;

import com.library.backend.security.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
class LanguageControllerTest {
    private static final String ENGLISH = "english";
    private static final String FINNISH = "finnish";
    private static final String SWEDISH = "swedish";

    private static final String ENGLISH_JA = "英語";
    private static final String FINNISH_JA = "フィンランド語";

    private static final String ENGLISH_AR = "الإنجليزية";
    private static final String FINNISH_AR = "الفنلندية";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LanguageRepository repository;

    @ParameterizedTest
    @CsvSource({
            "en, english, finnish",
            "ja-JP, 英語, フィンランド語",
            "ar-u-nu-arab, الإنجليزية, الفنلندية"
    })
    void testGetAllLanguages(final String locale, final String expectedFirst, final String expectedSecond) throws Exception {

        final Language english = new Language(ENGLISH, ENGLISH_JA, ENGLISH_AR);
        final Language finnish = new Language(FINNISH, FINNISH_JA, FINNISH_AR);

        when(repository.findAll()).thenReturn(Arrays.asList(english, finnish));

        mockMvc.perform(get("/api/language/all/" + locale))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].language").value(expectedFirst))
                .andExpect(jsonPath("$[0].languageKey").value(ENGLISH))
                .andExpect(jsonPath("$[1].language").value(expectedSecond))
                .andExpect(jsonPath("$[1].languageKey").value(FINNISH));
    }

    @ParameterizedTest
    @CsvSource({
            "en, english",
            "ja-JP, 英語"
    })
    void testGetLanguageByName(final String locale, final String expectedValue) throws Exception {

        final Language english = new Language(ENGLISH, ENGLISH_JA, ENGLISH_AR);
        when(repository.findById(ENGLISH)).thenReturn(Optional.of(english));

        mockMvc.perform(get("/api/language/english/" + locale))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.language").value(expectedValue))
                .andExpect(jsonPath("$.languageKey").value(ENGLISH));
    }

    @Test
    void testGetLanguageByNameNotFound() throws Exception {
        when(repository.findById(SWEDISH)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/language/swedish/en"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}



