package com.library.backend.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorRepository repository;

    @Test
    public void testGetAllAuthors() throws Exception {
        Author author1 = new Author("J.K.", "Rowling");
        author1.setAuthorId(1);
        Author author2 = new Author("Tove", "Jansson");
        author2.setAuthorId(2);

        when(repository.findAll()).thenReturn(Arrays.asList(author1, author2));
        mockMvc.perform(get("/api/author"))
                .andExpect(jsonPath("$[0].authorId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("J.K."))
                .andExpect(jsonPath("$[0].lastName").value("Rowling"))
                .andExpect(jsonPath("$[1].authorId").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Tove"))
                .andExpect(jsonPath("$[1].lastName").value("Jansson"));
    }

    @Test
    public void testGetAuthorById() throws Exception {
        Author author = new Author("Tove", "Jansson");
        author.setAuthorId(1);
        when(repository.findById(1)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/author/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.firstName").value("Tove"))
                .andExpect(jsonPath("$.lastName").value("Jansson"));
    }
}
