package com.example.demo.entities;


import javax.persistence.*;

@Entity 
public class Citizen { 
	@Id 
	private int afm; 
	
	private String firstName;
	private String surName; 
	private String email; 
	private String password; 
	
	@OneToOne(mappedBy="citizen")
	private Cart cart;
	
	public Citizen() {}
	
	public Citizen(int a, String fn, String sn, String e, String p) {
        this.afm = a; 
        this.firstName = fn; 
        this.surName = sn; 
        this.email = e; 
        this.password = p; 
    }

	public int getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	} 

}
