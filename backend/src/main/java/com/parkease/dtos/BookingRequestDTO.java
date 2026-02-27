package com.parkease.dtos;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long slotId;
    private String startTime;
    private String endTime;
}