package com.example.springecommerce.Model.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DailyReportResponse {
    private String transactionDate;
    private String email;
    private String productName;
    private String price;
    private String quantity;
    private String grandTotal;

}
