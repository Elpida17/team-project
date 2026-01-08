package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
