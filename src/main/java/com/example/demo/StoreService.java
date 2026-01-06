package com.example.demo;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;

	public void addStore(Store s) throws Exception {
		//Check first if exist already
		Optional<Store> byId = storeRepository.findById(s.getAfm());
		if(!byId.isPresent()) {
			storeRepository.save(s);
	} else {
		System.out.println("Το μαγαζί αυτό υπάρχει ήδη.");
	}
	

}
}
