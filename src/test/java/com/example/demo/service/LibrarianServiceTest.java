package com.example.demo.service;

import com.example.demo.pojo.entity.Librarian;
import com.example.demo.repository.LibrarianRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibrarianServiceTest {

    @Autowired
    private LibrarianService librarianService;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Test
    void addLibrarian_shouldSaveLibrarian() {
        Librarian librarian = new Librarian();
        librarian.setName("Service Test");
        librarian.setRole("Admin");

        Librarian saved = librarianService.addLibrarian(librarian);

        assertNotNull(saved.getId());
        assertEquals("Service Test", saved.getName());
        assertEquals("Admin", saved.getRole());
    }

    @Test
    void calculateTotalPenalties_shouldReturnZeroInitially() {
        double total = librarianService.calculateTotalPenalties();
        assertEquals(0.0, total);
    }
}
