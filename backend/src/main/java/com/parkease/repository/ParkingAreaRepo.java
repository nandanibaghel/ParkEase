package com.parkease.repository;

import com.parkease.models.ParkingArea;
import com.parkease.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ParkingAreaRepo extends JpaRepository<ParkingArea, Long> {
    Optional<ParkingArea> findByOwner(User owner);
}