package com.example.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfCardInsertion;
    private Loan loan;

    public ReaderDto(Long id, String name, String surname, LocalDate dateOfCardInsertion) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfCardInsertion = dateOfCardInsertion;
    }
}