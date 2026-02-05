package com.example.demo;

import com.example.demo.entities.Order;
import com.example.demo.entities.Citizen;
import com.example.demo.services.OrderService;
import com.example.demo.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CitizenRepository citizenRepository;

    // Endpoint for Checkout 
   
    @PostMapping("/orders/checkout/{afm}")
    public ResponseEntity<String> checkout(@PathVariable Integer afm) {
        try {
            System.out.println("---- Αίτημα Ολοκλήρωσης για ΑΦΜ: " + afm + " ----");
            
            
            Citizen citizen = citizenRepository.findById(afm)
                    .orElseThrow(() -> new RuntimeException("Ο πολίτης δεν βρέθηκε!"));

            
            Order finalOrder = orderService.createOrderFromCart(citizen);
            
            return ResponseEntity.ok("Η παραγγελία ολοκληρώθηκε επιτυχώς! Κωδικός: " + finalOrder.getId());

        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Σφάλμα: " + e.getMessage());
        }
    }

    // Endpoint for History 
    @GetMapping("/orders-history/{afm}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Integer afm) {
        return ResponseEntity.ok(orderService.getOrdersByCitizen(afm));
    }
}