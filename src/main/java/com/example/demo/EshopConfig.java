package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;

@Configuration
public class EshopConfig implements CommandLineRunner{

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public void run(String... args) throws Exception {
		
		//Create citizen1 and citizen2
        Citizen citizen1 = new Citizen(111222333, "Elpida", "Konstantinidou", "elpida@gmail.com", "1122");
        Citizen citizen2 = new Citizen(444555666, "Anna", "Panagiotidou", "anna@gmail.com", "4455");
        citizenRepository.save(citizen1);
        citizenRepository.save(citizen2);
        
        //Create store1 and store2
        Store store1 = new Store(121212121, "Plaisio", "PlaisioOwner", "1212");
        Store store2 = new Store(232323232, "Jumbo", "JumboOwner", "2323");
        storeRepository.save(store1);
        storeRepository.save(store2);
        
        //Create product1, product2, product3 and product4
        Product product1 = new Product("Laptop", "HP", 750.0, "description of Laptop HP", 10, store1);
        Product product2 = new Product("SmartPhone", "Iphone", 1100.00, "description of Smartphone Iphone", 5, store1);
        Product product3 = new Product("Toy", "Barbie", 15.0, "description of Toy Barbie", 35, store2);
        Product product4 = new Product("Cup", "Cup", 5.0, "description of Cup Cup", 60, store2);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
	}   
}



