package com.example.demo.entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity 
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "citizen")
	@JsonIgnoreProperties("cart")
	private Citizen citizen;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("cart")
	private List<CartItem> products;
	
	private double total_price;
	
    public Cart() {}
	
	public Cart(Citizen citizen, double total_price) {
		this.citizen = citizen;
        this.total_price = total_price;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public List<CartItem> getProducts() {
		return products;
	}

	public void setProducts(List<CartItem> products) {
		this.products = products;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

}




