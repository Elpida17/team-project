package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.AddToCartDto;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional 
public class CartService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addProductToCart(AddToCartDto dto) {
        // Search for Citizen and Product
        Citizen citizen = citizenRepository.findById(dto.getCitizenAfm())
                .orElseThrow(() -> new RuntimeException("Ο πελάτης δεν βρέθηκε."));

        Product product = productRepository.findById(dto.getProductType())
                .orElseThrow(() -> new RuntimeException("Το προϊόν " + dto.getProductType() + " δεν υπάρχει."));

        // Stock Check
        if (product.getNumberOfProducts() < dto.getQuantity()) {
            throw new RuntimeException("Ανεπαρκές απόθεμα! Διαθέσιμα τεμάχια: " + product.getNumberOfProducts());
        }

        // Search or create cart
        Cart cart = citizen.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCitizen(citizen);
            cart.setTotal_price(0.0);
            cartRepository.save(cart);
        }

        // Create CartItem
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(dto.getQuantity());
        cartItemRepository.save(cartItem);

        // Price Update
        double itemCost = product.getPrice() * dto.getQuantity();
        cart.setTotal_price(cart.getTotal_price() + itemCost);
        cartRepository.save(cart);
    }
    public Cart getCartByCitizen(Integer citizenAfm) {
        Citizen citizen = citizenRepository.findById(citizenAfm)
            .orElseThrow(() -> new RuntimeException("Ο πελάτης δεν βρέθηκε"));
        
        Cart cart = citizen.getCart();
        if (cart == null) {
            throw new RuntimeException("Το καλάθι είναι άδειο");
        }
        return cart;
    }
    
    public void removeItemFromCart(Integer citizenAfm, String productType) {
        // Find Citizen and Cart
        Citizen citizen = citizenRepository.findById(citizenAfm)
                .orElseThrow(() -> new RuntimeException("Ο πολίτης δεν βρέθηκε"));
        
        Cart cart = citizen.getCart();
        if (cart == null || cart.getProducts() == null) {
            throw new RuntimeException("Το καλάθι είναι ήδη άδειο");
        }

        // Find certain product in cart
        CartItem itemToRemove = cart.getProducts().stream()
                .filter(item -> item.getProduct().getType().equals(productType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Το προϊόν δεν βρέθηκε στο καλάθι"));

        // Update total price
        double reduction = itemToRemove.getProduct().getPrice() * itemToRemove.getQuantity();
        cart.setTotal_price(cart.getTotal_price() - reduction);

        // Remove from cart
        cart.getProducts().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);

        // save new cart
        cartRepository.save(cart);
    }
}