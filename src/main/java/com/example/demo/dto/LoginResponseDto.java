package com.example.demo.dto;

public class LoginResponseDto {
    private String message;
    private String role; // "citizen" ή "store"
    private Integer afm;

    public LoginResponseDto(String message, String role, Integer afm) {
        this.message = message;
        this.role = role;
        this.afm = afm;
    }

    // Getters 
    public String getMessage() { return message; }
    public String getRole() { return role; }
    public Integer getAfm() { return afm; }
}