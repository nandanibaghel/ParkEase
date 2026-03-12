package com.parkease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parkease.dtos.ParkingAreaRequestDTO;
import com.parkease.models.ParkingArea;
import com.parkease.models.ParkingSlot;
import com.parkease.models.User;
import com.parkease.repository.ParkingAreaRepo;
import com.parkease.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingAreaService {

	
    private final ParkingAreaRepo parkingAreaRepository;
	private final UserRepository userRepository;

    public ParkingArea createParkingArea(ParkingAreaRequestDTO dto, String email) {
    	
    	User owner = userRepository.findByEmail(email).orElse(null);
    	
    	if(owner==null) {
    		throw new UsernameNotFoundException("user not found ");
    	}
        ParkingArea area = new ParkingArea();
        area.setLotName(dto.getLotName());
        area.setAddress(dto.getAddress());
        area.setCity(dto.getCity());
        area.setPincode(dto.getPincode());
        area.setTotalSlots(dto.getTotalSlots());
        area.setOwner(owner);

        return parkingAreaRepository.save(area);
    }
}
