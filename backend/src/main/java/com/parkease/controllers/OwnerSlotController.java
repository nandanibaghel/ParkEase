<<<<<<< HEAD
package com.parkease.controllers;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingSlot;
import com.parkease.service.OwnerSlotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owners/slots")
@RequiredArgsConstructor
public class OwnerSlotController {

    private final OwnerSlotService ownerSlotService;

    @PostMapping("/add")
    public ResponseEntity<String> addSlot(
            @RequestBody ParkingSlotRequestDTO request,
            Authentication authentication) {
    	ownerSlotService.addSlot(request);
        return ResponseEntity.ok(
                "slot added "
        );
    }

    @GetMapping
    public ResponseEntity<List<ParkingSlot>> getOwnerSlots(
            Authentication authentication) {

        return ResponseEntity.ok(
                ownerSlotService.getOwnerSlots()
        );
    }

    @PutMapping("/{slotId}")
    public ResponseEntity<ParkingSlot> updateSlot(
            @PathVariable Long slotId,
            @RequestBody ParkingSlotRequestDTO request) {

        return ResponseEntity.ok(
                ownerSlotService.updateSlot(slotId, request)
        );
    }
    
    @DeleteMapping("/{slotId}")
    public ResponseEntity<Boolean> deleteSlot(
            @PathVariable Long slotId ) {

        return ResponseEntity.ok(
                ownerSlotService.deleteSlot(slotId)
        );
    }
    
=======
package com.parkease.controllers;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingSlot;
import com.parkease.service.OwnerSlotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owners/slots")
@RequiredArgsConstructor
public class OwnerSlotController {

    private final OwnerSlotService ownerSlotService;

    @PostMapping("/add")
    public ResponseEntity<ParkingSlot> addSlot(
            @RequestBody ParkingSlotRequestDTO request,
            Authentication authentication) {
        return ResponseEntity.ok(
                ownerSlotService.addSlot(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<ParkingSlot>> getOwnerSlots(
            Authentication authentication) {

        return ResponseEntity.ok(
                ownerSlotService.getOwnerSlots()
        );
    }

    @PutMapping("/{slotId}")
    public ResponseEntity<ParkingSlot> updateSlot(
            @PathVariable Long slotId,
            @RequestBody ParkingSlot slot) {

        return ResponseEntity.ok(
                ownerSlotService.updateSlot(slotId, slot)
        );
    }
>>>>>>> 94f254670e09825d37c4102ad2d3c7ce00be4e15
}