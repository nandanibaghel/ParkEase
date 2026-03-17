package com.parkease.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.parkease.models.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByParkingSlotParkingAreaId(Long areaId);
    List<Booking> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}