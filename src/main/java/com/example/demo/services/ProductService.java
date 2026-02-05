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

	 
	public List<Product> searchProducts(String type, String brand, Double minPrice, Double maxPrice) {
	    return productRepository.search(type, brand, minPrice, maxPrice);
	}
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}

	 // add product to store
	public void addProductToStore(Integer storeAfm, AddProductDto dto) {
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
	
	// Get all products for a specific store
	public List<Product> getProductsByStore(Integer storeAfm) {
		Store store = storeRepository.findById(storeAfm)
				.orElseThrow(() -> new RuntimeException("Το κατάστημα δεν βρέθηκε"));
		return store.getProduct();
	}
	
	// Update product quantity
	public void updateProductQuantity(String productType, int newQuantity) {
		if (newQuantity < 0) {
			throw new RuntimeException("Η ποσότητα δεν μπορεί να είναι αρνητική");
		}
			
		Product product = productRepository.findById(productType)
			.orElseThrow(() -> new RuntimeException("Το προϊόν δεν βρέθηκε"));
			
		product.setNumberOfProducts(newQuantity);
		productRepository.save(product);
	}
		
	// Add product to store
	public void addProductToStore(Integer storeAfm, Product product) {
		Store store = storeRepository.findById(storeAfm)
			.orElseThrow(() -> new RuntimeException("Το κατάστημα δεν βρέθηκε"));
			
		// Check if product type already exists
		if (productRepository.findById(product.getType()).isPresent()) {
			throw new RuntimeException("Το προϊόν με αυτόν τον τύπο υπάρχει ήδη");
		}
			
		product.setStore(store);
		productRepository.save(product);
	}

}
