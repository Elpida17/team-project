package com.example.demo;

import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;

@Entity 
public class Citizen { 
	@Id 
	private int afm; 
	
	private String firstName;
	private String surtName; 
	private String email; 
	private String password; 
	
	@OneToOne(mappedBy="citizen", mappedBy= CascadeType.ALL)
	private Cart cart;
	
	public Citizen() {}
	
	public Citizen(int a, String fn, String sn, String e, String p) {
		afm = a; 
		firstName = fn; 
		surtName = sn; 
		email = e; 
		password = p; 
	} 
	
	public int getAfm() {return afm;} 
	public String getFirstName() {return firstName;} 
	public String getSurtName() {return surtName;} 
	public String getEmail() {return email;} 
	public String getPassword() {return password;} 
	
}