package com.security.controller;

import com.security.entity.Product;
import com.security.exception.ProductNotFoundException;
import com.security.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product){
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> allProducts(){
        return  ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> productById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        return  ResponseEntity.ok(productService.findById(id));
    }

}
