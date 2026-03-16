package com.example.week6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.week6.model.Product;
import com.example.week6.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    public Product addProduct(Product product){
        return repo.save(product);
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProduct(int id){
        return repo.findById(id).orElse(null);
    }

    public Product updateProduct(int id,Product p){
        Product product = repo.findById(id).orElse(null);

        product.setName(p.getName());
        product.setPrice(p.getPrice());

        return repo.save(product);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }
}