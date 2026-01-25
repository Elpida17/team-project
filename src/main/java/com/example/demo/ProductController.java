package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddProductDto;
import com.example.demo.dto.ProductSearchDto;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.services.ProductService;
import com.example.demo.services.StoreService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private StoreService storeService;

	
		@PostMapping("/store/addProduct")
	    public ResponseEntity<String> addProductToStore(@RequestBody AddProductDto addProductDto) {
	        try {
	            productService.addProductToStore(addProductDto);
	            return ResponseEntity.ok("Το προϊόν προστέθηκε επιτυχώς!");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Σφάλμα: " + e.getMessage());
	        }
	    }
	
        
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
		

		@GetMapping("/view/{storeAFM}")
		public ResponseEntity<List<Product>> getProductsPerStore(@PathVariable Integer storeAFM){
			try {
				List<Product> store = storeService.getProductsPerStore(storeAFM);
				return ResponseEntity.ok(store);
			}catch (RuntimeException e) {
				return ResponseEntity.status(404).body(null);			}
		}

}
