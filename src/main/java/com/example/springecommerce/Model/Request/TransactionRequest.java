package com.example.springecommerce.Model.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {
    private LocalDate transactionDate;
    private Long userId;
    private Long productId;
    private Integer quantity;

}
