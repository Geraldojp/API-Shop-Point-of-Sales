package com.example.springecommerce.Controller;

import com.example.springecommerce.Model.Entity.Category;
import com.example.springecommerce.Model.Request.CategoryRequest;
import com.example.springecommerce.Model.Response.SuccessResponse;
import com.example.springecommerce.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @PostMapping
    public ResponseEntity save(@RequestBody CategoryRequest category) {
        Category newCategory = categoryService.save(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Saved", newCategory));
    }
    @GetMapping
    public ResponseEntity findAll() {
        Iterable<Category> find = categoryService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", find));
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Category find = categoryService.findById(id).get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", find));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Deleted", id + " has been deleted"));
    }
}
