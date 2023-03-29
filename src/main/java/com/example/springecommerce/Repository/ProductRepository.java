package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}