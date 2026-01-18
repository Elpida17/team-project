package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.ProductSearchDto;
import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
	
	@Autowired
    private ProductService productService;

	
		/*//addProductToStore
		@PostMapping(path="/addProductToStore")
		public void addProductToStore(@PathVariable String store, @RequestBody Product pt) throws Exception {
			productService.addProductToStore(pt);
		}*/
	
        
		/*search product
		 * Flexible search: Returns results even if the user provides only one criterion.
		 * Null or empty fields are ignored by the search filter.
		 */
		@PostMapping("/search")
        public List<Product> search(@RequestBody ProductSearchDto dto) {
            return productService.searchProducts(dto.type, dto.brand, dto.minPrice, dto.maxPrice);
        }
		
		@GetMapping("/getProducts")
		public List<Product> getAllProducts() throws Exception{
			return productService.getProducts();		}
		}
