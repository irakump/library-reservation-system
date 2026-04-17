package com.library.backend.book;

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

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = BookController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookRepository repository;

    @ParameterizedTest
    @CsvSource({"en-US,Test book1", "ja-JP,テストブック1", "ar-u-nu-arab,كتاب اختبار 1"})
    void shouldGetAllBooksInLocale(String lang, String titleLocalized) throws Exception {
        Book book = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);

        when(repository.findAll())
                .thenReturn(List.of(book));

        mockMvc.perform(get("/api/book/" + lang))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].title").value(titleLocalized)
                );
    }

    @Test
    void shouldGetBookByIsbn() throws Exception {
        final String expectedIsbn = "4444";

        Book book1 = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);
        Book book2 = new Book("2222", "Test book2", "テストブック2", "كتاب اختبار 2", 2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book3 = new Book("3333", "Test book3", "テストブック3", "كتاب اختبار 3", 2004, "Test book", "テストブック", "كتاب اختبار", "history", "english", true);
        Book book4 = new Book("4444", "Test book4", "テストブック4", "كتاب اختبار 4", 2024, "Test book", "テストブック", "كتاب اختبار", "history", "finnish", true);
        Book book5 = new Book("5555", "Test book5", "テストブック5", "كتاب اختبار 5", 2024, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book6 = new Book("6666", "Test book6", "テストブック6", "كتاب اختبار 6",2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);

        when(repository.findAll())
                .thenReturn(List.of(book1, book2, book3, book4, book5, book6));

        mockMvc.perform(get("/api/book/" + expectedIsbn))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[3].isbn").value(expectedIsbn)
                );
    }

    @Test
    void shouldGetYearsUsedByBooks() throws Exception {
        Book book1 = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);
        Book book2 = new Book("2222", "Test book2", "テストブック2", "كتاب اختبار 2", 2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book3 = new Book("3333", "Test book3", "テストブック3", "كتاب اختبار 3", 2004, "Test book", "テストブック", "كتاب اختبار", "history", "english", true);
        Book book4 = new Book("4444", "Test book4", "テストブック4", "كتاب اختبار 4", 2024, "Test book", "テストブック", "كتاب اختبار", "history", "finnish", true);
        Book book5 = new Book("5555", "Test book5", "テストブック5", "كتاب اختبار 5", 2024, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book6 = new Book("6666", "Test book6", "テストブック6", "كتاب اختبار 6",2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);

        when(repository.findAll())
                .thenReturn(List.of(book1, book2, book3, book4, book5, book6));

        mockMvc.perform(get("/api/book/years"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$").isArray(),
                        jsonPath("$[0]").value(2021),
                        jsonPath("$[1]").value(2010),
                        jsonPath("$[2]").value(2004),
                        jsonPath("$[3]").value(2024)
                );
    }

    @Test
    void shouldGetBooksByGenre() throws Exception {
        final String expectedGenre = "history";

        Book book3 = new Book("3333", "Test book3", "テストブック3", "كتاب اختبار 3", 2004, "Test book", "テストブック", "كتاب اختبار", "history", "english", true);
        Book book4 = new Book("4444", "Test book4", "テストブック4", "كتاب اختبار 4", 2024, "Test book", "テストブック", "كتاب اختبار", "history", "finnish", true);

        when(repository.findByGenre(expectedGenre))
                .thenReturn(List.of(book3, book4));

        mockMvc.perform(get("/api/book/genre/" + expectedGenre))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].genre").value(expectedGenre),
                        jsonPath("$[1].genre").value(expectedGenre)
                );
    }

    @Test
    void shouldGetBooksByYear() throws Exception {
        final int expectedYear = 2010;

        Book book2 = new Book("2222", "Test book2", "テストブック2", "كتاب اختبار 2", 2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book6 = new Book("6666", "Test book6", "テストブック6", "كتاب اختبار 6",2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);

        when(repository.findByYear(expectedYear))
                .thenReturn(List.of(book2, book6));

        mockMvc.perform(get("/api/book/year/" + expectedYear))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].year").value(expectedYear),
                        jsonPath("$[1].year").value(expectedYear)
                );
    }

    @Test
    void shouldGetBooksByLanguage() throws Exception {
        final String expectedLanguage = "english";

        Book book1 = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);
        Book book2 = new Book("2222", "Test book2", "テストブック2", "كتاب اختبار 2", 2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book3 = new Book("3333", "Test book3", "テストブック3", "كتاب اختبار 3", 2004, "Test book", "テストブック", "كتاب اختبار", "history", "english", true);
        Book book5 = new Book("5555", "Test book5", "テストブック5", "كتاب اختبار 5", 2024, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);
        Book book6 = new Book("6666", "Test book6", "テストブック6", "كتاب اختبار 6",2010, "Test book", "テストブック", "كتاب اختبار", "fantasy", "english", true);

        when(repository.findByLanguage(expectedLanguage))
                .thenReturn(List.of(book1, book2, book3, book5, book6));

        mockMvc.perform(get("/api/book/language/" + expectedLanguage))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].language").value(expectedLanguage),
                        jsonPath("$[1].language").value(expectedLanguage),
                        jsonPath("$[2].language").value(expectedLanguage),
                        jsonPath("$[3].language").value(expectedLanguage),
                        jsonPath("$[4].language").value(expectedLanguage)
                );
    }

    @Test
    void shouldFilterBooks() throws Exception {
        final String locale = "en-US";

        Book book1 = new Book("1111", "Test book1", "テストブック1", "كتاب اختبار 1", 2021, "Test book", "テストブック", "كتاب اختبار", "biography", "english", true);
        BookDTO dto1 = new BookDTO(book1);

        List<Book> filteredBooks = List.of(book1);

        when(bookService.findByFilters(null, null, "english", null, null))
                .thenReturn(filteredBooks);

        when(bookService.localizeBooks(filteredBooks, locale))
                .thenReturn(List.of(dto1));

        // test filtering in just one language
        mockMvc.perform(get("/api/book/filter/" + locale)
                        .param("language", "english"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].isbn").value("1111")
                );
    }
}
