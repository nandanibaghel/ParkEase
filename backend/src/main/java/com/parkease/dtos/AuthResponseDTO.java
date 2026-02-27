package com.parkease.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class AuthResponseDTO {
    private String token;
    private String message;

    public AuthResponseDTO(String token, String message) {
        this.message = message;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}