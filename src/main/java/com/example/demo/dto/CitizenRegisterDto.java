package com.example.demo.dto;

import javax.validation.constraints.*;

public class CitizenRegisterDto {
    
	//Ensuring the nine digit AFM
    @Min(value=100000000, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    @Max(value=999999999, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    public int afm;
    
    @NotBlank(message="Το πεδίο όνομα είναι υποχρεωτικό")
    public String firstName;
    
    @NotBlank(message="Το πεδίο επώνυμο είναι υποχρεωτικό")
    public String surName;
    
    //Ensuring that the email has an @ in it.
    @Email(message="Το email πρέπει να είναι έγκυρο")
    @NotBlank(message="Το πεδίο email είναι υποχρεωτικό")
    public String email;
    
    //Check for the password. Ensuring the 4 digit length
    @NotBlank(message="Το πεδίο password είναι υποχρεωτικό")
    @Size(min = 4, message = "Το password πρέπει να έχει τουλάχιστον 4 χαρακτήρες")
    public String password;
    
    public int getAfm() { return afm; }
    public String getFirstName() { return firstName; }
    public String getSurName() { return surName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

