package com.example.springecommerce.Model.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MonthlyReport {
    private String month;
    private String year;
}
