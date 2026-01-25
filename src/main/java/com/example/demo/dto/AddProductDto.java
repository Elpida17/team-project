package com.example.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddProductDto {
	
	//Αυτό πρέπει;
	@NotNull(message = "Ο ΑΦΜ του καταστήματος είναι υποχρεωτικός")
    private Integer storeAfm;
    
    @NotBlank(message = "Ο τύπος προϊόντος είναι υποχρεωτικός")
    private String type;
    
    @NotBlank(message = "Η μάρκα είναι υποχρεωτική")
    private String brand;
    
    @Positive(message = "Η τιμή πρέπει να είναι θετικός αριθμός")
    private double price;
    
    @NotBlank(message = "Η περιγραφή είναι υποχρεωτική")
    private String description;
    
    @Min(value = 1, message = "Ο αριθμός προϊόντων πρέπει να είναι τουλάχιστον 1")
    private int numberOfProducts;
    
    public Integer getStoreAfm() { return storeAfm; }
    public void setStoreAfm(Integer storeAfm) { this.storeAfm = storeAfm; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getNumberOfProducts() { return numberOfProducts; }
    public void setNumberOfProducts(int numberOfProducts) { this.numberOfProducts = numberOfProducts;
    }
}
