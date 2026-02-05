package com.example.demo;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CitizenRegisterDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.StoreRegisterDto;
import com.example.demo.entities.Citizen;
import com.example.demo.entities.Order;
import com.example.demo.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*Endpoint of the log in */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        LoginResponseDto response = userService.login(dto.afm, dto.password);
        return ResponseEntity.ok(response);
    }
    

    /*Endpoint for citizen Register */
    @PostMapping("/citizenRegister")
    public ResponseEntity<String> citizenRegister(@Valid @RequestBody CitizenRegisterDto dto) {
        String result = userService.citizenRegister(dto); //
        return ResponseEntity.ok(result);
    }

    /*Endpoint for store Register */
    @PostMapping("/storeRegister")
    public ResponseEntity<String> storeRegister(@Valid @RequestBody StoreRegisterDto dto) {
        String result = userService.storeRegister(dto); //
        return ResponseEntity.ok(result);
    }

    /*Endpoint for get Citizens - Testing */
    @GetMapping("/getCitizens")
    public List<Citizen> getAllCitizens() throws Exception {
        return userService.getCitizens(); //
    }
}