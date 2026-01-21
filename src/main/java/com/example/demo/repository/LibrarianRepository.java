package com.example.demo.repository;

import com.example.demo.pojo.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    boolean existsByRole(String role);
}

