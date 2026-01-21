package com.example.demo.repository;

import com.example.demo.pojo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByCategory(String category);
    List<Book> findByAvailable(boolean available);
}

