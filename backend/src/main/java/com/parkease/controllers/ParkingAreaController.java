package com.parkease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkease.dtos.ParkingAreaRequestDTO;
import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingArea;
import com.parkease.models.ParkingSlot;
import com.parkease.service.ParkingAreaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/parking-areas")
@RequiredArgsConstructor
public class ParkingAreaController {


    private final ParkingAreaService parkingAreaService;

    @PostMapping
    public ResponseEntity<String> createParkingArea(
            @RequestBody ParkingAreaRequestDTO dto) {

        ParkingArea area = parkingAreaService.createParkingArea(dto);
        return ResponseEntity.ok("Lot " + area.getLotName() + "added successfully with " + area.getTotalSlots() + " parking slots");
    } 
    
  
    
    
}
