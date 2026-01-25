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
    public void addProductToStore(AddProductDto dto) {
        // 1. Έλεγχος αν το κατάστημα υπάρχει
        Store store = storeRepository.findById(dto.getStoreAfm()).orElseThrow(() -> new RuntimeException("Το κατάστημα με ΑΦΜ " + dto.getStoreAfm() + " δεν βρέθηκε!"));
       
        // 2. Έλεγχος αν το προϊόν υπάρχει ήδη (με βάση το type)
        if (productRepository.existsById(dto.getType())) {
            throw new RuntimeException("Το προϊόν '" + dto.getType() + "' υπάρχει ήδη!");
        }
        
        // 3. Δημιουργία νέου προϊόντος
        Product product = new Product();
        product.setType(dto.getType());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setNumberOfProducts(dto.getNumberOfProducts());
        product.setStore(store);
        
        // 4. Αποθήκευση
        productRepository.save(product);
        
    }

}
