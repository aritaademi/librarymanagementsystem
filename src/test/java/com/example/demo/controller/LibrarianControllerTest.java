package com.example.demo.controller;

import com.example.demo.pojo.dto.CreateLibrarianRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibrarianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addLibrarian_shouldReturnOk() throws Exception {
        CreateLibrarianRequest request = new CreateLibrarianRequest();
        request.setName("Test Librarian");
        request.setRole("Admin");

        mockMvc.perform(post("/librarians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLibrarians_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/librarians"))
                .andExpect(status().isOk());
    }

    @Test
    void getTotalPenalties_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/librarians/totalPenalties"))
                .andExpect(status().isOk());
    }
}
