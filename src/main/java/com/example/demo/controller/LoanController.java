package com.example.demo.controller;

import com.example.demo.pojo.dto.BorrowBookRequest;
import com.example.demo.pojo.entity.Loan;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/borrow")
    public Loan borrow(@RequestBody BorrowBookRequest request) {
        return loanService.borrowBook(
                request.getBookId(),
                request.getMemberId()
        );
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }


    @PostMapping("/return/{loanId}")
    public Loan returnBook(@PathVariable Integer loanId) {
        return loanService.returnBook(loanId);
    }
}
