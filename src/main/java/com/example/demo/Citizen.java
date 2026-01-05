package com.example.demo;

import java.util.*;
import javax.persistence.*;

@Entity 
public class Citizen { 
	@Id 
	private int afm; 
	
	private String firstName;
	private String surName; 
	private String email; 
	private String password; 
	
	@OneToOne(mappedBy="citizen", cascade = CascadeType.ALL)
	private Cart cart;
	
	public Citizen() {}
	
	public Citizen(int a, String fn, String sn, String e, String p) {
		afm = a; 
		firstName = fn; 
		surName = sn; 
		email = e; 
		password = p; 
	} 
	
	public int getAfm() {return afm;} 
	public String getFirstName() {return firstName;} 
	public String getSurName() {return surName;} 
	public String getEmail() {return email;} 
	public String getPassword() {return password;} 
	
}