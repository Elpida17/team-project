package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreRegisterDto {
	
	//Ensuring the nine digit AFM
    @Min(value=100000000, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    @Max(value=999999999, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    public int afm;
    
    @NotBlank(message="Το πεδίο επωνυμία καταστήματος είναι υποχρεωτική")
    public String shopName;
    
    @NotBlank(message="Το πεδίο ιδιοκτήτητης είναι υποχρεωτικό")
    public String owner;
    
    //Ensuring that the email has an @ in it.
    @NotBlank(message="Το πεδίο password είναι υποχρεωτικό")
    @Size(min = 4, message = "Το password πρέπει να έχει τουλάχιστον 4 χαρακτήρες")
    public String password;
    
    public int getAfm() { return afm; }
    public String getShopName() { return shopName; }
    public String getOwner() { return owner; }
    public String getPassword() { return password; }

}
