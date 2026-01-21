
package com.example.demo.service;

import com.example.demo.pojo.entity.Book;
import com.example.demo.pojo.entity.Member;
import com.example.demo.pojo.entity.Loan;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Test
    void borrowBook_shouldCreateLoanAndUpdateBook() {
        // Create a test book
        Book book = new Book();
        book.setTitle("JUnit Book");
        book.setAuthor("Test Author");
        book.setCategory("Testing");
        book.setAvailable(true);
        book.setBorrowCount(0);
        book = bookRepository.save(book);

        // Create a test member
        Member member = new Member();
        member.setName("Test Member");
        member.setEmail("test@example.com");
        member.setUnpaidPenalty(0);
        member = memberRepository.save(member);

        // Borrow book
        Loan loan = loanService.borrowBook(book.getId(), member.getId());

        assertNotNull(loan);
        assertFalse(bookRepository.findById(book.getId()).get().isAvailable());
        assertEquals(1, bookRepository.findById(book.getId()).get().getBorrowCount());
    }
}
