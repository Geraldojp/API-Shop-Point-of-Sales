package com.example.springecommerce.Service;

import com.example.springecommerce.Exception.NotFoundException;
import com.example.springecommerce.Model.Entity.Category;
import com.example.springecommerce.Model.Request.CategoryRequest;
import com.example.springecommerce.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Category save(CategoryRequest category) {
        try {
            Category newCategory = modelMapper.map(category, Category.class);
            return categoryRepository.save(newCategory);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Category> findById(Long id) {
        try {
            Optional<Category> find = categoryRepository.findById(id);
            if (find.isEmpty()) {
            throw new NotFoundException("Data not found");
            }
            return find;
        }catch (NotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Category> findAll() {
        try {
            Iterable<Category> find = categoryRepository.findAll();
            if (find == null){
                throw new NotFoundException("Data not found");
            }
            return find;
        }catch (NotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Category> find = categoryRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException("Data not found");
            }
            categoryRepository.deleteById(id);
        }catch (NotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
