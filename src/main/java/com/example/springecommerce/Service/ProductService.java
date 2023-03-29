package com.example.springecommerce.Service;

import com.example.springecommerce.Exception.NotFoundException;
import com.example.springecommerce.Model.Entity.Category;
import com.example.springecommerce.Model.Entity.Price;
import com.example.springecommerce.Model.Entity.Product;
import com.example.springecommerce.Model.Entity.Stock;
import com.example.springecommerce.Model.Request.ProductRequest;
import com.example.springecommerce.Model.Request.ProductResponse;
import com.example.springecommerce.Repository.CategoryRepository;
import com.example.springecommerce.Repository.PriceRepository;
import com.example.springecommerce.Repository.ProductRepository;
import com.example.springecommerce.Repository.StockRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product save(ProductRequest product) {
        try {
            Product newProduct = modelMapper.map(product, Product.class);
            Price newPrice = modelMapper.map(product, Price.class);
            Stock newStock = modelMapper.map(product, Stock.class);

            Category category = categoryRepository.findById(product.getCategoryId()).get();

            newProduct.setPrice(newPrice);
            newProduct.setCategory(category);
            newProduct.setStock(newStock);

            stockRepository.save(newStock);
            priceRepository.save(newPrice);
            return productRepository.save(newProduct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Optional<Product> find = productRepository.findById(id);
            if (find.isEmpty())
                throw new NotFoundException("Data not found");
            return find;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductResponse> findAll() {
        try {
            List<Product> find = productRepository.findAll();
            if (find == null) {
                throw new NotFoundException("Data not found");
            }
            return find.stream()
                    .map(product ->
                        modelMapper.map(product, ProductResponse.class))
                    .collect(Collectors.toList()
                    );
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Product> find = productRepository.findById(id);
            if (find.isEmpty())
                throw new NotFoundException("Data not found");
            productRepository.deleteById(id);
        } catch (NotFoundException e){
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
