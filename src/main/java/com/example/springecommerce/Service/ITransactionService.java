package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Entity.TransactionDetail;
import com.example.springecommerce.Model.Request.TransactionRequest;
import com.example.springecommerce.Model.Request.TransactionReport;


import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    TransactionDetail save(TransactionRequest transaction);
    List<TransactionReport> findAll();
    List<TransactionReport> getDailyReport(LocalDate date);
    List<TransactionReport> getMonthlyReport(int month, int year);
    void delete(Long id);

}
