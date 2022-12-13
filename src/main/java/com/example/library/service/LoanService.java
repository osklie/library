package com.example.library.service;

import com.example.library.domain.Copy;
import com.example.library.domain.Loan;
import com.example.library.domain.Reader;
import com.example.library.repo.CopyRepository;
import com.example.library.repo.LoanRepository;
import com.example.library.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    ReaderRepository readerRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoan(Long id) {
        return loanRepository.findById(id);
    }

    public Loan saveLoan(final Loan loan) {
        Optional<Reader> readerOptional = readerRepository.findById(loan.getId_reader());
        Reader reader = readerOptional.get();
        loan.setReader(reader);
        Optional<Copy> copyOptional = copyRepository.findById(loan.getId_copy());
        Copy copy = copyOptional.get();
        loan.setCopy(copy);
        if(loan.getReturned().equals("No")) {
            copy.setStatus("Loaned");
        } else {
            copy.setStatus("In library");
            loan.setReturnOfDate(LocalDate.now());
        }
        return loanRepository.save(loan);
    }

    public void deleteLoan(final Long id) {
        loanRepository.deleteById(id);
    }

}