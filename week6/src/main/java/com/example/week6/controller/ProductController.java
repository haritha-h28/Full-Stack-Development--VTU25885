package com.example.week6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.week6.model.Product;
import com.example.week6.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return service.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id){
        return service.getProduct(id);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id,@RequestBody Product product){
        return service.updateProduct(id,product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return "Product Deleted";
    }
}
