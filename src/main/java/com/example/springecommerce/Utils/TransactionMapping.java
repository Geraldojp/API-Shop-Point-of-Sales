package com.example.springecommerce.Utils;

import com.example.springecommerce.Model.Entity.TransactionDetail;
import com.example.springecommerce.Model.Request.TransactionReport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class TransactionMapping {
    public List<TransactionReport> getTransactionResponses(List<TransactionDetail> find) {
        return find.stream()
                .map(transactionDetail -> {
                    TransactionReport transactionReport = new TransactionReport();
                    transactionReport.setTransactionDate(String.valueOf(transactionDetail.getTransaction().getTransactionDate()));
                    transactionReport.setEmail(transactionDetail.getTransaction().getUser().getAuth().getEmail());
                    transactionReport.setProductName(transactionDetail.getProduct().getProductName());
                    transactionReport.setPrice(String.valueOf(transactionDetail.getProduct().getPrice().getPrice()));
                    transactionReport.setQuantity(String.valueOf(transactionDetail.getQuantity()));
                    transactionReport.setGrandTotal(String.valueOf(transactionDetail.getTransaction().getGrandTotal()));
                    return transactionReport;
                }).collect(Collectors.toList());
    }
}
