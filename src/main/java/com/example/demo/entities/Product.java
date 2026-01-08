package com.example.demo.entities;

import javax.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Product {

	@Id
	private String type;
	
	private String brand;
	private double price;
	private String description;
	private int numberOfProducts;
	
	public Product(String type, String brand, double price, String description, int numberOfProducts, Store store) {
		super();
		this.type = type;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.numberOfProducts = numberOfProducts;
		//Store αρχικοποίηση
	}
	
	public Product() {}
	public String getBrand() {return brand;} 
	public double getPrice() {return price;} 
	public String getDescription() {return description;} 
	public int getNumberOfProducts() {return numberOfProducts;} 
	
	
}
