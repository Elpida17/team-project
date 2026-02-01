package com.example.demo;

import com.example.demo.entities.Order;
import com.example.demo.entities.Citizen;
import com.example.demo.services.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

 
    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody Citizen citizen) {
      
        Order finalOrder = orderService.createOrderFromCart(citizen);
        
        return ResponseEntity.ok(finalOrder);
    }
    
    @GetMapping("/history/{afm}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Integer afm) {
        List<Order> history = orderService.getOrdersByCitizen(afm);
        return ResponseEntity.ok(history);
    }
}