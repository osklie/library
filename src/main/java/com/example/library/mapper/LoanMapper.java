package com.example.library.mapper;

import com.example.library.domain.Loan;
import com.example.library.domain.LoanDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {
    public Loan mapToLoan(final LoanDto loanDto) {
        return new Loan(
                loanDto.getId(),
                loanDto.getId_copy(),
                loanDto.getId_reader(),
                loanDto.getReturned());
    }

    public LoanDto mapToLoanDto(final Loan loan) {
        return new LoanDto(
                loan.getId(),
                loan.getId_copy(),
                loan.getId_reader(),
                loan.getReturned());
    }

    public List<LoanDto> mapToLoanDtoList(final List<Loan> loanList) {
        return loanList.stream()
                .map(t -> new LoanDto(t.getId(), t.getId_copy(), t.getId_reader(), t.getReturned()))
                .collect(Collectors.toList());
    }

}