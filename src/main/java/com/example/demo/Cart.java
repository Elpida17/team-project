package com.example.demo;

import java.util.*;
import javax.persistence.*;

@Entity
public class Cart {
	private int price; 
	
	@OneToOne
    @JoinColumn(name = "citizen_afm")
    private Citizen citizen;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

	public Cart() {}
	
	public Cart(int p) {
		price = p; 
	} 
	
	public int getPrice() {return price;} 

}

	
	