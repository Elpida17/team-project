package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	             Store findByAfm(int afm);
}
