package com.example.demo.services;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CitizenRegisterDto;
import com.example.demo.dto.StoreRegisterDto;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Store;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.repository.StoreRepository;

@Service
public class UserService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private StoreRepository storeRepository;

    // Εγγραφή Μαγαζιού
    public String storeRegister(StoreRegisterDto dto) {
        Store store = new Store();
        
        store.setAfm(dto.afm);
        store.setShopName(dto.shopName);
        store.setOwner(dto.owner);
        store.setPassword(dto.password);
  
        storeRepository.save(store);
        
        return "Το κατάστημα " + dto.shopName + " δημιουργήθηκε επιτυχώς!";
    }
    // Εγγραφή Πολίτη
    public String citizenRegister(CitizenRegisterDto dto) {
        // 1. Φτιάχνουμε ένα νέο αντικείμενο Citizen (Entity)
        Citizen citizen = new Citizen();
        
        // 2. Μεταφέρουμε τα δεδομένα από το DTO στο Entity
        citizen.setAfm(dto.afm); 
        citizen.setFirstName(dto.firstName);
        citizen.setSurName(dto.surName);
        citizen.setEmail(dto.email);
        citizen.setPassword(dto.password);
        
        //Αποθηκεύεται στη βάση μέσω του Repository
        citizenRepository.save(citizen);
        
        return "Ο πολίτης " + dto.firstName + " εγγράφηκε με επιτυχία!";
    }

    /*// Κοινή μέθοδος Login (Παράδειγμα)
    public Object login(String email, String password) {
        return null; 
    }*/
}