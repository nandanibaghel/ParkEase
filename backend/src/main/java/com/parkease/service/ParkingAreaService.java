package com.parkease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkease.dtos.ParkingAreaRequestDTO;
import com.parkease.models.ParkingArea;
import com.parkease.models.ParkingSlot;
import com.parkease.repository.ParkingAreaRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingAreaService {

	@Autowired
    private ParkingAreaRepo parkingAreaRepository;

    public ParkingArea createParkingArea(ParkingAreaRequestDTO dto) {

        ParkingArea area = new ParkingArea();
        area.setLotName(dto.getLotName());
        area.setAddress(dto.getAddress());
        area.setCity(dto.getCity());
        area.setPincode(dto.getPincode());
        area.setTotalSlots(dto.getTotalSlots());

        return parkingAreaRepository.save(area);
    }
}
