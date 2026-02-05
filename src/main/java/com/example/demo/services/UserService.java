package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CitizenRegisterDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.StoreRegisterDto;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Order;
import com.example.demo.entities.Store;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class UserService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private StoreRepository storeRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    public LoginResponseDto login(Integer afm, String password) {
        // Search for Citizen
        Optional<Citizen> citizenOpt = citizenRepository.findById(afm);
        if (citizenOpt.isPresent()) {
            Citizen citizen = citizenOpt.get();
            if (citizen.getPassword().equals(password)) {
                return new LoginResponseDto("Επιτυχής σύνδεση ως Πολίτης", "citizen", citizen.getAfm());
            } else {
                return new LoginResponseDto("Λάθος κωδικός πρόσβασης", null, null);
            }
        }

        
        Optional<Store> storeOpt = storeRepository.findById(afm);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            if (store.getPassword().equals(password)) {
                return new LoginResponseDto("Επιτυχής σύνδεση ως Κατάστημα", "store", store.getAfm());
            } else {
                return new LoginResponseDto("Λάθος κωδικός πρόσβασης", null, null);
            }
        }

        
        return new LoginResponseDto("Ο ΑΦΜ δεν βρέθηκε στο σύστημα", null, null);
    }

   
    // Store Register
    public String storeRegister(StoreRegisterDto dto) {
    	if (storeRepository.existsById(dto.getAfm())) {
            throw new RuntimeException("Το ΑΦΜ του μαγαζιού αυτού υπάρχει ήδη.");
    	}
        Store store = new Store(); 
        
        store.setAfm(dto.getAfm());        
        store.setShopName(dto.getShopName());
        store.setOwner(dto.getOwner());
        store.setPassword(dto.getPassword());
        
        storeRepository.save(store);
        
        return "Το κατάστημα " + dto.shopName + " δημιουργήθηκε επιτυχώς!";
    }
    // Citizen Register
    public String citizenRegister(CitizenRegisterDto dto) {
        if (citizenRepository.existsById(dto.getAfm())) {
            throw new RuntimeException("Το ΑΦΜ υπάρχει ήδη.");
        }
        Citizen citizen = new Citizen();
        citizen.setAfm(dto.getAfm());
        citizen.setFirstName(dto.getFirstName());
        citizen.setSurName(dto.getSurName());
        citizen.setEmail(dto.getEmail());
        citizen.setPassword(dto.getPassword());
        citizenRepository.save(citizen);
    
        return "Ο πολίτης " + dto.firstName + " εγγράφηκε με επιτυχία!";
    }
    
    
    
    public List<Citizen> getCitizens(){
    	return citizenRepository.findAll();
    }
    
    public List<Order> getCitizenOrders(Integer afm) {
        return orderRepository.findByCitizenAfm(afm);
    }
}