package com.parkease.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.parkease.models.ParkingSlot;
public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByParkingAreaId(Long areaId);
    List<ParkingSlot> findByIsAvailableTrue();
}
