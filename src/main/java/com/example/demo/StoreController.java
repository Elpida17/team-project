package com.example.demo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

//Since this is the controller, it is the place where we add our end-points
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StoreController {

     @Autowired
     private StoreService storeService;
	
     //Register Store
	@PostMapping(path="/addStore")
	public void addStore(@RequestBody Store s) throws Exception {
		storeService.addStore(s);
	}

}