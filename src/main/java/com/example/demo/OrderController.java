package com.example.demo;

import com.example.demo.entities.Order;
import com.example.demo.entities.Citizen;
import com.example.demo.services.OrderService;
import com.example.demo.repository.CitizenRepository; // Χρειαζόμαστε αυτό για να βρούμε τον Citizen
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CitizenRepository citizenRepository;

    /**
     * Endpoint για το Checkout.
     * Δέχεται ένα JSON με το AFM, π.χ. { "afm": 111222333 }
     */
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody Map<String, Integer> payload) {
        Integer afm = payload.get("afm");
        
        // 1. Βρίσκουμε τον Citizen από τη βάση δεδομένων
        Citizen citizen = citizenRepository.findById(afm)
                .orElseThrow(() -> new RuntimeException("Ο πολίτης με ΑΦΜ " + afm + " δεν βρέθηκε."));

        // 2. Εκτελούμε το Checkout μέσω του Service (που έχει την @Transactional)
        Order finalOrder = orderService.createOrderFromCart(citizen);
        
        return ResponseEntity.ok(finalOrder);
    }

    /**
     * Endpoint για το Ιστορικό Παραγγελιών
     */
    @GetMapping("/history/{afm}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Integer afm) {
        List<Order> history = orderService.getOrdersByCitizen(afm);
        return ResponseEntity.ok(history);
    }
}