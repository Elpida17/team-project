package com.example.demo.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Product; 
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository; 

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private ProductRepository productRepository; 

    @Transactional 
    public Order createOrderFromCart(Citizen citizen) {
        
        Cart cart = cartRepository.findByCitizen(citizen);
        
        if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Το καλάθι είναι άδειο!");
        }

        Order order = new Order();
        order.setCitizen(citizen); 
        order.setPaymentStatus("PAID");

        List<OrderItem> orderItems = new ArrayList<>();
        
        for (CartItem cartItem : cart.getProducts()) {
            Product product = cartItem.getProduct();
            int quantityOrdered = cartItem.getQuantity();

            // Quantity check
            if (product.getNumberOfProducts() < quantityOrdered) {
                throw new RuntimeException("Ανεπαρκές απόθεμα για το προϊόν: " + product.getType());
            }

            //Decrease quantity
            product.setNumberOfProducts(product.getNumberOfProducts() - quantityOrdered);
            productRepository.save(product);

            // Create ORDER ITEM
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantityOrdered);
            orderItem.setPrice(product.getPrice()); 
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.calculateTotal();

        Order savedOrder = orderRepository.save(order);

        // Clear Cart
        cart.getProducts().clear();
        cart.setTotal_price(0.0);
        cartRepository.save(cart);

        return savedOrder;
    }
    
    public List<Order> getOrdersByCitizen(Integer afm) {
        Citizen citizen = citizenRepository.findById(afm)
                .orElseThrow(() -> new RuntimeException("Ο πολίτης δεν βρέθηκε"));
        
        return orderRepository.findByCitizen(citizen);
    }
}