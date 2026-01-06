package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

//Interface for the Citizen to connect with the JPA Citizen
//Only basic implementations are inherited
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
	
}
