package com.example.springecommerce.Utils;

import com.example.springecommerce.Model.Entity.TransactionDetail;
import com.example.springecommerce.Model.Request.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class TransactionMapping {
    public List<TransactionResponse> getTransactionResponses(Page<TransactionDetail> find) {
        return find.stream()
                .map(transactionDetail -> {
                    TransactionResponse transactionResponse = new TransactionResponse();
                    transactionResponse.setTransactionDate(String.valueOf(transactionDetail.getTransaction().getTransactionDate()));
                    transactionResponse.setEmail(transactionDetail.getTransaction().getUser().getAuth().getEmail());
                    transactionResponse.setProductName(transactionDetail.getProduct().getProductName());
                    transactionResponse.setPrice(String.valueOf(transactionDetail.getProduct().getPrice().getPrice()));
                    transactionResponse.setQuantity(String.valueOf(transactionDetail.getQuantity()));
                    transactionResponse.setGrandTotal(String.valueOf(transactionDetail.getTransaction().getGrandTotal()));
                    return transactionResponse;
                }).collect(Collectors.toList());
    }
}
