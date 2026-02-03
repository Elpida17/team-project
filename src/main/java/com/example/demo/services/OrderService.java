package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CitizenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CitizenRepository citizenRepository;

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
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            
            orderItem.setPrice(cartItem.getProduct().getPrice()); 
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.calculateTotal();

        
        Order savedOrder = orderRepository.save(order);

        
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
