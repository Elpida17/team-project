package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Store {
	
@Id
   private String afm;

   private String shopName; 
   private String owner;   
   private String password;
   
   @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
   private Product product;
   
   /*@OneToOne
   @JoinColumn(name = "product")
   private Product product;*/
   
   public Store() {}
   
   
   public Store(String a, String s, String o, String p) {
		afm = a;
		shopName = s;
		owner = o;
		password = p;
   }
   
   public String getAfm() {
	   return afm;
   }
   
   public String getshopName() {
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
   


