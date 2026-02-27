package com.parkease.repository;


import com.parkease.models.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingAreaRepo extends JpaRepository<ParkingArea, Long> {

   
}
