package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Entity.Product;
import com.example.springecommerce.Model.Request.ProductRequest;
import com.example.springecommerce.Model.Request.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product save(ProductRequest product);
    Optional<Product> findById(Long id);
    List<ProductResponse> findAll();
    void delete(Long id);

}
