package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
/*
	public void addProductToStore(Product pt) {
		// TODO Auto-generated method stub
		
	}

	 
	 public void updateNumberOfProducts(){
	 }*/
	 
	public List<Product> searchProducts(String type, String brand, Double minPrice, Double maxPrice) {
	    return productRepository.search(type, brand, minPrice, maxPrice);
	}
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}

}
