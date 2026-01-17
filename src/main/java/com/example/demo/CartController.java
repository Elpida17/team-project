package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.entities.Cart;
import com.example.demo.services.CartService; // <--- ΠΡΟΣΟΧΗ ΕΔΩ

@RestController
@RequestMapping("/cart") 
public class CartController {

    @Autowired
    private CartService cartService; 
    
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto) {
        cartService.addProductToCart(addToCartDto);
        return ResponseEntity.ok("Το προϊόν προστέθηκε στο καλάθι.");
    }
    
    @GetMapping("/view")
    public List<Cart> getCart() throws Exception{
    	return cartService.viewCart();
    }
}