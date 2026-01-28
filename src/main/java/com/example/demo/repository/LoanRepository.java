package com.example.demo.repository;

import com.example.demo.pojo.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    // All active (not returned) loans for a member
    List<Loan> findByMemberIdAndReturnDateIsNull(Integer memberId);
    //creates:
    //SELECT *
    //FROM loan
    //WHERE member_id = ?
    //  AND return_date IS NULL;
}
