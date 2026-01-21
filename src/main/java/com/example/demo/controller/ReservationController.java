package com.example.demo.controller;

import com.example.demo.pojo.entity.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation reserveBook(@RequestParam Integer bookId,
                                   @RequestParam Integer memberId) {
        return reservationService.reserveBook(bookId, memberId);
    }

    @DeleteMapping("/{reservationId}")
    public String cancelReservation(@PathVariable Integer reservationId,
                                    @RequestParam Integer memberId) {

        reservationService.cancelReservation(reservationId, memberId);
        return "Reservation cancelled successfully";
    }


    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
}

