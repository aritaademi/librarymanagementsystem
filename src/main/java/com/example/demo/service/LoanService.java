package com.example.demo.service;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.entity.Book;
import com.example.demo.pojo.entity.Loan;
import com.example.demo.pojo.entity.Member;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LoanRepository loanRepository;





    // LoanService.java
    public Loan borrowBook(Integer bookId, Integer memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (!book.isAvailable()) {
            throw new BusinessLogicException("Book is already borrowed");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setBorrowDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(2));
        loan.setReturnDate(null);

        // Mark book as unavailable
        book.setAvailable(false);

        // Increment borrow count
        book.setBorrowCount(book.getBorrowCount() + 1);

        // Save book and loan
        bookRepository.save(book);
        return loanRepository.save(loan);
    }


    public Loan returnBook(Integer loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new BusinessLogicException("Book already returned");
        }

        loan.setReturnDate(LocalDate.now());

        double penalty = calculatePenalty(loan);

        if (penalty > 0) {
            Member member = loan.getMember();
            member.setUnpaidPenalty(member.getUnpaidPenalty() + penalty);
            memberRepository.save(member);
        }

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }




    public double calculatePenalty(Loan loan) {
        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(
                    loan.getDueDate(), loan.getReturnDate());
            return daysLate * 1.5;
        }
        return 0;
    }
}

