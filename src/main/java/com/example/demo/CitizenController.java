package com.example.demo;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Citizen;

//Since this is the controller, it is the place where we add our end-points
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CitizenController {

    /* @Autowired
     private CitizenService cs;
	
     //Register Citizen
	@PostMapping(path="/addCitizen")
	public void addCitizen(@RequestBody Citizen cz) throws Exception {
		cs.addCitizen(cz);
	} */

}