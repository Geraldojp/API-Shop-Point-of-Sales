package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}