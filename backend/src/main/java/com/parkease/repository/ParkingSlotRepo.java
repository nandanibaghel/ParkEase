<<<<<<< HEAD
package com.parkease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.ParkingSlot;

public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Long> {
    
}
=======
package com.parkease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.ParkingSlot;

public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByOwnerId(Long ownerId);
    List<ParkingSlot> findByIsAvailableTrue();
}
>>>>>>> 94f254670e09825d37c4102ad2d3c7ce00be4e15
