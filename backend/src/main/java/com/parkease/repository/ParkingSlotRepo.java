package com.parkease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.ParkingSlot;

public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Long> {
    
}
