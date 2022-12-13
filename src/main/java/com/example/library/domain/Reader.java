package com.example.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "READERS")
public class Reader {

    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfCardInsertion = LocalDate.now();
    private Loan loan;

    public Reader() {}

    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @NotNull
    @Column(name = "DATE")
    public LocalDate getDateOfCardInsertion() {
        return dateOfCardInsertion;
    }

    public void setDateOfCardInsertion(LocalDate dateOfCardInsertion) {
        this.dateOfCardInsertion = dateOfCardInsertion;
    }
    @Transient
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}