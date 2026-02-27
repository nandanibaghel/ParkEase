package com.parkease.dtos;

import lombok.Data;

@Data
public class ParkingAreaRequestDTO {

    private String lotName;
    private String address;
    private String city;
    private String pincode;
    private int totalSlots;
	
    
    
}
