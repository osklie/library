package com.example.library.controller;

import com.example.library.domain.LoanDto;
import com.example.library.exception.ObjectNotFoundException;
import com.example.library.mapper.LoanMapper;
import com.example.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/loan")
public class LoanController {
    @Autowired
    private LoanService dbServiceLoan;

    @Autowired
    private LoanMapper loanMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getLoans")
    public List<LoanDto> getLoans() {
        return loanMapper.mapToLoanDtoList(dbServiceLoan.getAllLoans());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getLoan")
    public LoanDto getLoan(@RequestParam Long loanId) throws ObjectNotFoundException {
        return loanMapper.mapToLoanDto(dbServiceLoan.getLoan(loanId).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteLoan")
    public void deleteLoan(@RequestParam Long loanId) {
        dbServiceLoan.deleteLoan(loanId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateLoan")
    public LoanDto updateLoan(@RequestBody LoanDto loanDto) {
        return loanMapper.mapToLoanDto(dbServiceLoan.saveLoan(loanMapper.mapToLoan(loanDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createLoan", consumes = APPLICATION_JSON_VALUE)
    public void createLoan(@RequestBody LoanDto loanDto) {
        dbServiceLoan.saveLoan(loanMapper.mapToLoan(loanDto));
    }
}