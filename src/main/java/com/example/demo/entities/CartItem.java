package com.example.demo.entities;

import javax.persistence.*;

/**
 * Η κλάση CartItem λειτουργεί ως συνδετικός κρίκος (bridge) μεταξύ Cart και Product.
 * 1. Επιτρέπει την αποθήκευση επιπλέον πληροφοριών για τη σχέση(quantity).
 * 2. Αποφεύγει την άμεση σύνδεση Many-to-Many μεταξύ Cart και Product, η οποία θα 
 * περιόριζε τη δυνατότητα να έχουμε πολλαπλά ίδια προϊόντα στο ίδιο καλάθι.
 */

@Entity
public class CartItem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int quantity;
    
    public CartItem() {}
    
    /*Προσθέτουμε στον constructor το Product και το Cart, έτσι ώστε
     * όταν δημιουργούμε ένα αντικείμενο, να μπορούμε να τα ορίσουμε όλα μαζί
     */
    public CartItem(Product product, Cart cart, int quantity) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
 }