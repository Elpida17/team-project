package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Store;
import com.example.demo.services.StoreService;

//Since this is the controller, it is the place where we add our end-points
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StoreController {

     @Autowired
     private StoreService storeService;
	
     //Register Store
	@PostMapping(path="/addStore")
	public String addStore(@RequestBody Store s) throws Exception {
		try {
	        storeService.addStore(s);
	        return "Το κατάστημα " + s.getShopName() + " με ΑΦΜ " + s.getAfm() + " εγγράφηκε επιτυχώς!";
	    } catch (Exception e) {
	        return "Σφάλμα κατά την εγγραφή: " + e.getMessage();
	    }
	}
	
	//Get Stores
	@GetMapping(path="/stores")
	public List<Store> getAllStore()  throws Exception{
		return storeService.getAllStores();
	} 
}