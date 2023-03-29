package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
    List<TransactionDetail> findByTransaction_TransactionDate(LocalDate transactionDate);
    List<TransactionDetail> findByTransaction_TransactionDateBetween(LocalDate transactionDateStart, LocalDate transactionDateEnd);
    @Query(value = "SELECT SUM(t.grand_total) FROM transaction t WHERE t.transaction_date = :date", nativeQuery = true)
    Double getTotalDailyTransaction(@Param("date") LocalDate transactionDate);
    @Query(value = "SELECT SUM(t.grand_total) FROM transaction t WHERE t.transaction_date BETWEEN :start AND :end", nativeQuery = true)
    Double getTotalMonthlyTransaction(@Param("start") LocalDate transactionDateStart, @Param("end") LocalDate transactionDateEnd);
}