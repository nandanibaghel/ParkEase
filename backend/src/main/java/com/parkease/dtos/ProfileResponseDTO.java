package com.parkease.dtos;

import lombok.Data;

@Data
public class ProfileResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private String createdAt;
}