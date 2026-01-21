package com.example.demo.service;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.entity.Book;
import com.example.demo.pojo.entity.Member;
import com.example.demo.pojo.entity.Reservation;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Reservation reserveBook(Integer bookId, Integer memberId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        if (book.isAvailable()) {
            throw new BusinessLogicException("Book is available, no need to reserve");
        }

        if (reservationRepository.existsByBookIdAndMemberId(bookId, memberId)) {
            throw new BusinessLogicException("You already reserved this book");
        }

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setMember(member);
        reservation.setReservationDate(LocalDate.now());

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Integer reservationId, Integer memberId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        if (!reservation.getMember().getId().equals(memberId)) {
            throw new BusinessLogicException("You can only cancel your own reservation");
        }

        reservationRepository.delete(reservation);
    }


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
