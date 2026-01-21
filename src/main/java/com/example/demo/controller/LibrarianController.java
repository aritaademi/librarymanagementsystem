package com.example.demo.controller;

import com.example.demo.pojo.dto.CreateLibrarianRequest;
import com.example.demo.pojo.entity.Librarian;
import com.example.demo.pojo.entity.Reservation;
import com.example.demo.service.LibrarianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;


    @PostMapping
    public Librarian addLibrarian(@Valid @RequestBody CreateLibrarianRequest request) {
        Librarian librarian = new Librarian();
        librarian.setName(request.getName());
        librarian.setRole(request.getRole());
        return librarianService.addLibrarian(librarian);
    }


    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public Librarian getLibrarian(@PathVariable Integer id) {
        return librarianService.getLibrarianById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteLibrarian(@PathVariable Integer id) {
        librarianService.deleteLibrarian(id);
        return "Librarian deleted successfully";
    }

    // Interesting features
    @PostMapping("/approveReservation/{reservationId}")
    public Reservation approveReservation(@PathVariable Integer reservationId) {
        return librarianService.approveReservation(reservationId);
    }

    @GetMapping("/totalPenalties")
    public double getTotalPenalties() {
        return librarianService.calculateTotalPenalties();
    }
}
