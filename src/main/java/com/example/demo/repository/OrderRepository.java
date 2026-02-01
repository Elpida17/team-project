package com.example.demo.repository;

import com.example.demo.entities.Order;
import com.example.demo.entities.Citizen; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
 
    List<Order> findByCitizen(Citizen citizen);
    
    
    List<Order> findByCitizenAfm(Integer afm);
    
}