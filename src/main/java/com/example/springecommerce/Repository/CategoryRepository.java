package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}