package com.parkease.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {

    private Long id;

    private String slotNumber;

    private String parkingAreaName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    
    private Double pricePerHour;

    private String status;
    
    private Double totalCost;
}