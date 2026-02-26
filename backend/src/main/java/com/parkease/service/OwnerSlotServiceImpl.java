package com.parkease.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingSlot;
import com.parkease.models.User;
import com.parkease.models.VehicleType;
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

    @Override
    public ParkingSlot addSlot(ParkingSlotRequestDTO request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        ParkingSlot slot = new ParkingSlot();
        slot.setIsAvailable(request.getIsAvailable());
        slot.setOwner(owner);
        slot.setPricePerHour(request.getPricePerHour());
        slot.setSlotNumber(request.getSlotNumber());
        slot.setVehicleType(VehicleType.valueOf(request.getVehicleType()));

        return slotRepo.save(slot);
    }

    @Override
    public List<ParkingSlot> getOwnerSlots() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        return slotRepo.findByOwnerId(owner.getId());
    }

    @Override
    public ParkingSlot updateSlot(Long slotId, ParkingSlot updatedSlot) {

        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.setPricePerHour(updatedSlot.getPricePerHour());
        slot.setIsAvailable(updatedSlot.getIsAvailable());
        slot.setVehicleType(updatedSlot.getVehicleType());

        return slotRepo.save(slot);
    }
}
