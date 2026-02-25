package com.parkease.dtos;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    
}
