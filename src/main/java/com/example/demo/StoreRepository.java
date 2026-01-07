package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Store;

//Interface for the Citizen to connect with the JPA Citizen
//Only basic implementations are inherited
public interface StoreRepository extends JpaRepository<Store, String> {
	
}
