package com.example.demo.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	private String type;
	
	private String brand;
	private double price;
	private String description;
	private int numberOfProducts;
	
	@ManyToOne
    @JoinColumn(name = "store_afm") 
	@JsonIgnore
    private Store store;
	
	public Product() {}
	
	public Product(String type, String brand, double price, String description, int numberOfProducts, Store store) {
		this.type = type;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.numberOfProducts = numberOfProducts;
		this.store = store;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}
