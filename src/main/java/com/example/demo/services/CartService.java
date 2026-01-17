package com.example.demo.services;

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
        Citizen citizen = citizenRepository.findById(dto.getCitizenAfm())
                .orElseThrow(() -> new RuntimeException("Ο πελάτης με ΑΦΜ " + dto.getCitizenAfm() + " δεν βρέθηκε!"));

        Product product = productRepository.findById(dto.getProductType())
                .orElseThrow(() -> new RuntimeException("Το προϊόν " + dto.getProductType() + " δεν βρέθηκε!"));

        Cart cart = citizen.getCart(); 
        
        if (cart == null) {
            cart = new Cart();
            cart.setCitizen(citizen);
            cart.setTotal_price(0.0);
            cartRepository.save(cart); 
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(dto.getQuantity());

        cartItemRepository.save(cartItem);


        double itemCost = product.getPrice() * dto.getQuantity();
        cart.setTotal_price(cart.getTotal_price() + itemCost);
        cartRepository.save(cart);
    }
}