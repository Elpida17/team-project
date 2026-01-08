package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.Id;

@Entity
public class Product {

	@Id
	private String type;
	
	private String brand;
	private double price;
	private String description;
	private int numberOfProducts;
	
	@OneToOne
    @JoinColumn(name = "store") 
    private Store store;
	
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
