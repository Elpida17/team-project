package com.example.demo;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.example.demo.entities.Citizen;

@Service
public class CitizenService {

	@Autowired
	private CitizenRepository citizenRepository;

	public void addCitizen(Citizen c) throws Exception {
		//Check first if exist already
		Optional<Citizen> byId = citizenRepository.findById(c.getAfm());
		if(!byId.isPresent())
			citizenRepository.save(c);
	}
	
}