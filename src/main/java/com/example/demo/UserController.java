package com.example.demo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CitizenRegisterDto;
import com.example.demo.dto.StoreRegisterDto;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

		@Autowired
		private UserService userService;

		/*@PostMapping("/login")
		public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
			return userService.login(dto.afm, dto.password);
		}
		*/

		@PostMapping("/citizenRegister")
		public String citizenRegister(@Valid @RequestBody CitizenRegisterDto dto) {
		    // Στείλε ΟΛΟ το dto στο service
		    return userService.citizenRegister(dto); 
		}

		@PostMapping("/storeRegister")
		public String storeRegister(@Valid @RequestBody StoreRegisterDto dto) {
			return userService.storeRegister(dto); 
		}

}

