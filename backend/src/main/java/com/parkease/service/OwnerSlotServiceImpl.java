package com.parkease.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.dtos.ParkingSlotResponseDTO;
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
    public ParkingSlotResponseDTO addSlot(ParkingSlotRequestDTO request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        ParkingArea area = areaRepo.findById(request.getAreaId())
                .orElseThrow(() -> new RuntimeException("Parking area not found"));

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(request.getSlotNumber());
        slot.setPricePerHour(request.getPricePerHour());
        slot.setIsAvailable(request.getIsAvailable());
        slot.setVehicleType(VehicleType.valueOf(request.getVehicleType()));
        slot.setParkingArea(area);

        slot = slotRepo.save(slot);
        return mapToDTO(slot);
        
    }

    @Override
    public List<ParkingSlotResponseDTO> getOwnerSlots(Long areaId) {
    	

        User owner = getCurrentOwner(); // from JWT / SecurityContext
        
        
        ParkingArea area= areaRepo.findById(areaId).orElse(null);
        if(area == null) {
        	throw new RuntimeException("invalid args");
        	
        }
        if(area.getOwner().getId() != owner.getId()) {
        	throw new RuntimeException("user not autherize");
        }
        
        List<ParkingSlot> slots  = slotRepo.findByParkingArea(area);
        

        return slots.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    private User getCurrentOwner() {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	User user = userRepository.findByEmail(email).orElse(null);
    	if(email ==null || user == null) {
    		throw new RuntimeException("user not found");
    	}
    	return user;
    }
    
    private ParkingSlotResponseDTO mapToDTO(ParkingSlot slot) {

        ParkingSlotResponseDTO dto = new ParkingSlotResponseDTO();
        dto.setId(slot.getId());
        dto.setSlotNumber(slot.getSlotNumber());
        dto.setPrice(slot.getPricePerHour());
        dto.setIsAvailable(slot.getIsAvailable());
        dto.setVehicleType(slot.getVehicleType().name());
       
        dto.setParkingAreaId(slot.getParkingArea().getId());
        dto.setParkingAreaName(slot.getParkingArea().getLotName());

        return dto;
    }

    @Override
    public ParkingSlotResponseDTO updateSlot(Long slotId, ParkingSlotRequestDTO updatedSlot) {

        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        
        if(updatedSlot.getPricePerHour() != null) {
        	slot.setPricePerHour(updatedSlot.getPricePerHour());        	
        } 
        if(updatedSlot.getIsAvailable() !=null) {   	
        	slot.setIsAvailable(updatedSlot.getIsAvailable());
        } 
        if(updatedSlot.getVehicleType() !=null) {
        	slot.setVehicleType(VehicleType.valueOf(updatedSlot.getVehicleType()));        	
        }

        slot = slotRepo.save(slot);
        return mapToDTO(slot);
    }

    @Override
    public Boolean deleteSlot(Long slotId) {

        if (!slotRepo.existsById(slotId)) {
            throw new RuntimeException("Slot not found");
        }

        slotRepo.deleteById(slotId);
        return true;
    }
}
