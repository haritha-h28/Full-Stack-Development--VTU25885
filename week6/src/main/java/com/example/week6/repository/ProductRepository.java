package com.example.week6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.week6.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
