package com.parkease.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {

    private Long slotId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}