package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
	              Citizen findByAfm(String afm);
	
}
