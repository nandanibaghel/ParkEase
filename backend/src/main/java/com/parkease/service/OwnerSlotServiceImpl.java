package com.parkease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingArea;
import com.parkease.models.ParkingSlot;
import com.parkease.models.User;
import com.parkease.models.VehicleType;
import com.parkease.repository.ParkingAreaRepo;
import com.parkease.repository.ParkingSlotRepo;
import com.parkease.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerSlotServiceImpl implements OwnerSlotService {

    @Autowired
    private ParkingSlotRepo slotRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParkingAreaRepo areaRepo;

    @Override
    @Transactional
    public Boolean deleteSlot(Long slotId) {
        slotRepo.deleteById(slotId);
        return true;
    }

    @Override
    @Transactional
    public ParkingSlot addSlot(ParkingSlotRequestDTO request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Find existing area for this owner or create a new one automatically
        ParkingArea area = areaRepo.findByOwner(owner)
                .orElseGet(() -> {
                    ParkingArea newArea = new ParkingArea();
                    newArea.setLotName(owner.getFullName() + "'s Parking");
                    newArea.setOwner(owner);
                    return areaRepo.save(newArea);
                });

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(request.getSlotNumber());
        slot.setVehicleType(VehicleType.valueOf(request.getVehicleType()));
        slot.setPricePerHour(request.getPricePerHour());
        slot.setIsAvailable(true);
        slot.setParkingArea(area);
        return slotRepo.save(slot);
    }

    @Override
    @Transactional
    public List<ParkingSlot> getOwnerSlots() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Get only this owner's parking area slots
        ParkingArea area = areaRepo.findByOwner(owner).orElse(null);
        if (area == null) return List.of();
        return slotRepo.findByParkingArea(area);
    }

    @Override
    @Transactional
    public ParkingSlot updateSlot(Long slotId, ParkingSlotRequestDTO updatedSlot) {
        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setPricePerHour(updatedSlot.getPricePerHour());
        slot.setIsAvailable(updatedSlot.getIsAvailable());
        slot.setVehicleType(VehicleType.valueOf(updatedSlot.getVehicleType()));
        return slotRepo.save(slot);
    }
}