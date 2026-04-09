package com.library.backend.author;

import com.library.backend.book.Book;
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
        value = AuthorController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorRepository repository;

    @Test
    public void testGetAllAuthors() throws Exception {
        Author author1 = new Author("J.K.", "Rowling", "J.K.", "ローリング", "ج.ك.", "رولينغ");
        author1.setAuthorId(1);
        Author author2 = new Author("Tove", "Jansson", "トーベ", "ヤンソン", "توفه", "يانسون");
        author2.setAuthorId(2);

        when(repository.findAll()).thenReturn(Arrays.asList(author1, author2));
        mockMvc.perform(get("/api/author/all/en-US"))
                .andExpect(jsonPath("$[0].authorId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("J.K."))
                .andExpect(jsonPath("$[0].lastName").value("Rowling"))
                .andExpect(jsonPath("$[1].authorId").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Tove"))
                .andExpect(jsonPath("$[1].lastName").value("Jansson"));
    }

    @Test
    public void testGetAuthorById() throws Exception {
        Author author = new Author("Tove", "Jansson", "トーベ", "ヤンソン", "توفه", "يانسون");
        author.setAuthorId(1);
        when(repository.findById(1)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/author/1/en-US"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.firstName").value("Tove"))
                .andExpect(jsonPath("$.lastName").value("Jansson"));
    }

    /*@Test
    public void testGetAuthorById_WithBooks() throws Exception {
        Author author = new Author("J.K.", "Rowling", "J.K.", "ローリング", "ج.ك.", "رولينغ");
        author.setAuthorId(1);

        Book book1 = new Book("123", "Book 1", "Book 1", "Book 1", 2000, "Desc", "Desc", "Desc", "Fantasy", "English", true);
        Book book2 = new Book("456", "Book 2", "Book 2", "Book 2", 2001, "desc", "Desc", "Desc", "fantasy", "English", true);

        author.setBooks(Arrays.asList(book1, book2));

        when(repository.findById(1)).thenReturn(Optional.of(author));


        mockMvc.perform(get("/api/author/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.books").isArray())
                .andExpect(jsonPath("$.books.length()").value(2));
    }*/

}


