package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
	
	@Autowired
    private ProductService ps;
	
		//addProductToStore
		@PostMapping(path="/addProductToStore")
		public void addProductToStore(@PathVariable String store, @RequestBody Product pt) throws Exception {
			ps.addProductToStore(pt);
		}

}
