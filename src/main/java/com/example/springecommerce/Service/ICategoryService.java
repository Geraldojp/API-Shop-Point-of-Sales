package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Entity.Category;
import com.example.springecommerce.Model.Entity.Product;
import com.example.springecommerce.Model.Request.CategoryRequest;
import com.example.springecommerce.Model.Request.ProductRequest;

import java.util.Optional;

public interface ICategoryService {
    Category save(CategoryRequest category);
    Optional<Category> findById(Long id);
    Iterable<Category> findAll();
    void delete(Long id);
}
