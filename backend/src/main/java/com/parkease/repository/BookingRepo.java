<<<<<<< HEAD
package com.parkease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    
}
=======
package com.parkease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkease.models.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByParkingSlotOwnerId(Long ownerId);
    List<Booking> findByUserId(Long userId);
}
>>>>>>> 94f254670e09825d37c4102ad2d3c7ce00be4e15
