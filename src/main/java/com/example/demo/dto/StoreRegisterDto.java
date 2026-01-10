package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreRegisterDto {
	
	//Εξασφαλίζουμε ότι ο χρήστης θα δώσει έναν αριθμό με 9 ψηφία
    @Min(value=100000000, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    @Max(value=999999999, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    public int afm;
    
    @NotBlank(message="Το πεδίο επωνυμία καταστήματος είναι υποχρεωτική")
    public String shopName;
    
    @NotBlank(message="Το πεδίο ιδιοκτήτητης είναι υποχρεωτικό")
    public String owner;
    
    //Ελέγχει ότι το πεδίο δεν είναι κενό ("") και ότι δεν έχει μόνο κενά διαστήματα (" ").
    @NotBlank(message="Το πεδίο password είναι υποχρεωτικό")
    @Size(min = 4, message = "Το password πρέπει να έχει τουλάχιστον 4 χαρακτήρες")
    public String password;

}
