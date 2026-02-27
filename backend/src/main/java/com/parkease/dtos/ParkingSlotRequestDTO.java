package com.parkease.dtos;


import lombok.Data;

@Data
public class ParkingSlotRequestDTO {

  
    private String slotNumber;

    
    private String vehicleType;

   
    private Double pricePerHour;

    private Boolean isAvailable;
    private Long areaId;
}