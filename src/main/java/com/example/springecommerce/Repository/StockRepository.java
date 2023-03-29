package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}