package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.dto.CreateBookRequest;
import com.example.demo.pojo.entity.Book;
import com.example.demo.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book createBook(@Valid @RequestBody CreateBookRequest req) {
        Book book = new Book();
        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setCategory(req.getCategory());
        book.setAvailable(true); // default available
        return bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    // BookController.java
    @GetMapping("/popular")
    public List<Book> getPopularBooks(@RequestParam(defaultValue = "5") int limit) {
        return bookRepository.findAll().stream()
                .sorted((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()))
                .limit(limit)
                .toList();
    }


    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/search")
    public List<Book> searchByTitle(@RequestParam String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Filter by category
    @GetMapping("/category")
    public List<Book> filterByCategory(@RequestParam String category) {
        return bookRepository.findByCategory(category);
    }

    // Filter by availability
    @GetMapping("/available")
    public List<Book> filterByAvailability(@RequestParam boolean available) {
        return bookRepository.findByAvailable(available);
    }

    // Optional: Filter by category AND availability
    @GetMapping("/filter")
    public List<Book> filterByCategoryAndAvailability(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean available) {

        if (category != null && available != null) {
            return bookRepository.findAll().stream()
                    .filter(b -> b.getCategory().equalsIgnoreCase(category) && b.isAvailable() == available)
                    .toList();
        } else if (category != null) {
            return bookRepository.findByCategory(category);
        } else if (available != null) {
            return bookRepository.findByAvailable(available);
        } else {
            return bookRepository.findAll();
        }
    }
}
