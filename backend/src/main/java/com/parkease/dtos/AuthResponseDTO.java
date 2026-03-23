package com.parkease.dtos;
import lombok.Data;
@Data
public class AuthResponseDTO {
    private String token;
    private String message;
    private String role;
    private String fullName;
    private String email;

    public AuthResponseDTO(String token, String message, String role, String fullName, String email) {
        this.token = token;
        this.message = message;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
    }
}
