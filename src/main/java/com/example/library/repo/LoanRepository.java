package com.example.library.repo;

import com.example.library.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Override
    List<Loan> findAll();

    @Override
    Loan save(Loan loan);

    @Override
    Optional<Loan> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}