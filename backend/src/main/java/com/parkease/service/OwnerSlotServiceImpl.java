package com.parkease.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
	public Boolean deleteSlot(Long slotId) {
		return true;
	}
	
    @Override
    public ParkingSlot addSlot(ParkingSlotRequestDTO request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        ParkingArea area = areaRepo.findById(request.getAreaId())
                .orElseThrow(() -> new RuntimeException("Parking area not found"));

        ParkingSlot slot = new ParkingSlot();
        slot.setIsAvailable(request.getIsAvailable());
        slot.setParkingArea(area);
        slot.setPricePerHour(request.getPricePerHour());
        slot.setSlotNumber(request.getSlotNumber());
        slot.setVehicleType(VehicleType.valueOf(request.getVehicleType()));

        return slotRepo.save(slot);
    }

//    @Override
//    public List<ParkingSlot> getOwnerSlots() {
//
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        User owner = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Owner not found"));
//
//        return slotRepo.findByOwnerId(owner.getId());
//    }
    @Override
    public List<ParkingSlot> getOwnerSlots() {
    	// TODO Auto-generated method stub
    	return null;
    }

    @Override
    public ParkingSlot updateSlot(Long slotId, ParkingSlotRequestDTO updatedSlot) {

        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.setPricePerHour(updatedSlot.getPricePerHour());
        slot.setIsAvailable(updatedSlot.getIsAvailable());
        slot.setVehicleType(VehicleType.valueOf( updatedSlot.getVehicleType()));

        return slotRepo.save(slot);
    }


	
}
