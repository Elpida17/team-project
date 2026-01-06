package com.example.demo;

import java.util.Set;
import javax.persistence.*;

@Entity 
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "citizen")
	private Citizen citizen;
	
	@OneToMany(mappedBy="cart")
	private Set<Product> products;
	
	private int total_price;
	
    public Cart() {}
	
	public Cart(Citizen citizen, int total_price) {
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

}




