package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.entities.Cart;
import com.example.demo.services.CartService; 

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/cart") 
public class CartController {

    @Autowired
    private CartService cartService; 
    
    
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto) {
        try {
            System.out.println("Προσπάθεια προσθήκης: " + addToCartDto.getProductType());
            
            cartService.addProductToCart(addToCartDto);
            return ResponseEntity.ok("Το προϊόν προστέθηκε στο καλάθι.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Σφάλμα: " + e.getMessage());
        }
    }
    
    @GetMapping("/view/{citizenAfm}")
    public ResponseEntity<Cart> getCartByCitizen(@PathVariable Integer citizenAfm) {
        try {
            Cart cart = cartService.getCartByCitizen(citizenAfm);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
    
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam int citizenAfm, @RequestParam String productType) {
        cartService.removeItemFromCart(citizenAfm, productType);
        return ResponseEntity.ok("Το προϊόν αφαιρέθηκε.");
    }
}