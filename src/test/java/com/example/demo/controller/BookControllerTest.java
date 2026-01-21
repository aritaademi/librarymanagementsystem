
package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBooks_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    void getBookById_shouldReturnOk() throws Exception {
        // Make sure a book with ID 1 exists in DB for this test
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk());
    }



    @Test
    void searchByTitle_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/books/search")
                        .param("title", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getPopularBooks_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/books/popular"))
                .andExpect(status().isOk());
    }
}
