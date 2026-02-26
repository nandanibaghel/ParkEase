package com.parkease.dtos;

import com.parkease.models.BookingStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Long id;
    private String slotNumber;
    private Double pricePerHour;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
    private Double totalCost;
}