package com.example.demo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    private double unpaidPenalty;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private Set<Loan> loans;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private Set<Reservation> reservations;

    public Member() {}
    // getters & setters

    public Member(String name, String email, double unpaidPenalty) {
        this.name = name;
        this.email = email;
        this.unpaidPenalty = unpaidPenalty;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getUnpaidPenalty() {
        return unpaidPenalty;
    }

    public void setUnpaidPenalty(double unpaidPenalty) {
        this.unpaidPenalty = unpaidPenalty;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }


}
