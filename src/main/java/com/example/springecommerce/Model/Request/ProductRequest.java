package com.example.springecommerce.Model.Request;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private Long categoryId;
    private Double price;
    private Integer totalStock;
}
