package com.example.demo.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Store {
	
@Id
   private int afm;

   private String shopName; 
   private String owner;   
   private String password;
   
   @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
   @JsonIgnore // Αυτό θα σταματήσει τον κύκλο στο Swagger/JSON
   private Product product;
   
   public Store() {}
   
   
   public Store(int afm, String shopName, String owner, String password) {
		this.afm = afm;
		this.shopName = shopName;
		this.owner = owner;
		this.password = password;
   }
   
   public int getAfm() {
	   return afm;
   }
   
   public String getShopName() {
	   return shopName;
   }
   
   public String getOwner() {
	   return owner;
   }
   
   public String getPassword() {
	   return password; }
   public Product getProduct() {
	   return product; }
   public void setProduct(Product p ) { product = p; }
}
   


