package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.entity.Librarian;
import com.example.demo.pojo.entity.Member;
import com.example.demo.pojo.entity.Reservation;
import com.example.demo.repository.LibrarianRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Basic CRUD
    public Librarian addLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    public Librarian getLibrarianById(Integer id) {
        return librarianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Librarian not found"));
    }

    public void deleteLibrarian(Integer id) {
        Librarian librarian = getLibrarianById(id);
        librarianRepository.delete(librarian);
    }

    // Interesting features:

    // Approve a reservation (remove it after processing)
    public Reservation approveReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        // Here you could add logic to notify member, or auto-borrow book
        reservationRepository.delete(reservation);
        return reservation;
    }

    //  Calculate total unpaid penalties for all members
    public double calculateTotalPenalties() {
        return memberRepository.findAll()
                .stream()
                .mapToDouble(Member::getUnpaidPenalty)
                .sum();
    }
}
