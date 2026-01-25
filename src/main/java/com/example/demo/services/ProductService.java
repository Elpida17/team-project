package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddProductDto;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreRepository storeRepository;
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

	 // Προσθήκη προϊόντος σε κατάστημα
	public void addProductToStore(Integer storeAfm, AddProductDto dto) {
	    // Το storeAfm έρχεται ως παράμετρος, όχι μέσα στο DTO
	    Store store = storeRepository.findById(storeAfm).orElseThrow(() -> new RuntimeException("Το κατάστημα δεν βρέθηκε"));
	    
	    Product product = new Product();
	    product.setType(dto.getType());
	    product.setBrand(dto.getBrand());
	    product.setPrice(dto.getPrice());
	    product.setDescription(dto.getDescription());
	    product.setNumberOfProducts(dto.getNumberOfProducts());
	    product.setStore(store);
	    
	    productRepository.save(product);
	}

}
