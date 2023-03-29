package com.example.springecommerce.Model.Request;

import lombok.Data;

@Data
public class ProductResponse {

    private String productName;
    private String productDescription;
    private String category;
    private String price;
    private String stock;
}
