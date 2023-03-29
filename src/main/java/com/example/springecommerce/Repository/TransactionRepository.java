package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionDateBetween(LocalDate transactionDateStart, LocalDate transactionDateEnd);
    List<Transaction> findByTransactionDate(LocalDate transactionDate);

}