package com.example.demo.dto;

import javax.validation.constraints.*;

public class CitizenRegisterDto {
    
	//Εξασφαλίζουμε ότι ο χρήστης θα δώσει έναν αριθμό με 9 ψηφία
    @Min(value=100000000, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    @Max(value=999999999, message="Ο ΑΦΜ πρέπει να είναι 9ψήφιος αριθμός")
    public int afm;
    
    @NotBlank(message="Το πεδίο όνομα είναι υποχρεωτικό")
    public String firstName;
    
    @NotBlank(message="Το πεδίο επώνυμο είναι υποχρεωτικό")
    public String surName;
    
    //Ελέγχει αυτόματα αν υπάρχει το σύμβολο @ και μια έγκυρη κατάληξη (π.χ. .com)
    @Email(message="Το email πρέπει να είναι έγκυρο")
    @NotBlank(message="Το πεδίο email είναι υποχρεωτικό")
    public String email;
    
    //Ελέγχει ότι το πεδίο δεν είναι κενό ("") και ότι δεν έχει μόνο κενά διαστήματα (" ").
    @NotBlank(message="Το πεδίο password είναι υποχρεωτικό")
    @Size(min = 4, message = "Το password πρέπει να έχει τουλάχιστον 4 χαρακτήρες")
    public String password;
}

