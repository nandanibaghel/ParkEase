package com.parkease.dtos;

import lombok.Data;

@Data
public class ParkingSlotResponseDTO {

    private Long id;
    private String slotNumber;
    private Double price;
    private Boolean isAvailable;
    private String vehicleType;

    // Parking area info (useful for owner dashboard)
    private Long parkingAreaId;
    private String parkingAreaName;
}