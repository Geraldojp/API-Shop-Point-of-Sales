package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Entity.TransactionDetail;
import com.example.springecommerce.Model.Request.TransactionRequest;
import com.example.springecommerce.Model.Request.TransactionResponse;


import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    TransactionDetail save(TransactionRequest transaction);
    List<TransactionResponse> findAll();
    List<TransactionResponse> getDailyReport(LocalDate date);
    List<TransactionResponse> getMonthlyReport(int month, int year);
    void delete(Long id);

}
