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
        // 1. Εύρεση Πολίτη & Προϊόντος
        Citizen citizen = citizenRepository.findById(dto.getCitizenAfm())
                .orElseThrow(() -> new RuntimeException("Ο πελάτης δεν βρέθηκε."));

        Product product = productRepository.findById(dto.getProductType())
                .orElseThrow(() -> new RuntimeException("Το προϊόν " + dto.getProductType() + " δεν υπάρχει."));

        // 2. ΕΛΕΓΧΟΣ ΑΠΟΘΕΜΑΤΟΣ (Stock Check)
        if (product.getNumberOfProducts() < dto.getQuantity()) {
            throw new RuntimeException("Ανεπαρκές απόθεμα! Διαθέσιμα τεμάχια: " + product.getNumberOfProducts());
        }

        // 3. Εύρεση ή Δημιουργία Καλαθιού
        Cart cart = citizen.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCitizen(citizen);
            cart.setTotal_price(0.0);
            cartRepository.save(cart);
        }

        // 4. Δημιουργία CartItem
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(dto.getQuantity());
        cartItemRepository.save(cartItem);

        // 5. Ενημέρωση Τιμής
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
        // 1. Βρίσκουμε τον πολίτη και το καλάθι του
        Citizen citizen = citizenRepository.findById(citizenAfm)
                .orElseThrow(() -> new RuntimeException("Ο πολίτης δεν βρέθηκε"));
        
        Cart cart = citizen.getCart();
        if (cart == null || cart.getProducts() == null) {
            throw new RuntimeException("Το καλάθι είναι ήδη άδειο");
        }

        // 2. Βρίσκουμε το συγκεκριμένο item στο καλάθι
        CartItem itemToRemove = cart.getProducts().stream()
                .filter(item -> item.getProduct().getType().equals(productType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Το προϊόν δεν βρέθηκε στο καλάθι"));

        // 3. Ενημερώνουμε το συνολικό ποσό του καλαθιού
        double reduction = itemToRemove.getProduct().getPrice() * itemToRemove.getQuantity();
        cart.setTotal_price(cart.getTotal_price() - reduction);

        // 4. Αφαίρεση από τη λίστα και διαγραφή από τη βάση
        cart.getProducts().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);

        // 5. Αποθήκευση του ανανεωμένου καλαθιού
        cartRepository.save(cart);
    }
}