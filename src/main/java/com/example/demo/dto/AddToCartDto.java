package com.example.demo.dto; 

public class AddToCartDto {
    private Integer citizenAfm; 
    private String productType; 
    private int quantity;       

   
    public AddToCartDto() {}

    // Getters και Setters
    public Integer getCitizenAfm() { return citizenAfm; }
    public void setCitizenAfm(Integer citizenAfm) { this.citizenAfm = citizenAfm; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}