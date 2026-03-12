package com.parkease.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.Booking;
import com.parkease.models.BookingStatus;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    
    boolean existsByParkingSlotIdAndStatusAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long parkingSlotId,
            BookingStatus status,
            LocalDateTime endTime,
            LocalDateTime startTime
            
    );
    List<Booking> findByUserId(Long userId);

}
