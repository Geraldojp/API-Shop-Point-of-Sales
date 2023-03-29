package com.example.springecommerce.Controller;

import com.example.springecommerce.Model.Entity.Product;
import com.example.springecommerce.Model.Request.ProductRequest;
import com.example.springecommerce.Model.Request.ProductResponse;
import com.example.springecommerce.Model.Response.SuccessResponse;
import com.example.springecommerce.Service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity save(@RequestBody ProductRequest product) {
        productService.save(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Saved", product));
    }
    @GetMapping
    public ResponseEntity findAll() {
        Iterable<ProductResponse> find = productService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", find));
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Product find = productService.findById(id).get();
        ProductResponse productResponse = modelMapper.map(find, ProductResponse.class);
//        ProductResponse productResponse = new ProductResponse();
//        if(find!=null){
//            productResponse.setProductName(find.getProductName());
//            productResponse.setProductDescription(find.getProductDescription());
//            productResponse.setCategoryName(find.getCategory().getCategoryName());
//            productResponse.setPrice(find.getPrice().getPrice());
//            productResponse.setTotalStock(find.getStock().getTotalStock());
//        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", productResponse));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Deleted", id + " has been deleted"));
    }

}
