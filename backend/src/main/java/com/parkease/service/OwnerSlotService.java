package com.parkease.service;

import java.util.List;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingSlot;

import org.springframework.transaction.annotation.Transactional;

public interface OwnerSlotService {

    ParkingSlot addSlot(ParkingSlotRequestDTO slot);

    @Transactional
    List<ParkingSlot> getOwnerSlots();

    Boolean deleteSlot(Long slotId);

    ParkingSlot updateSlot(Long slotId, ParkingSlotRequestDTO updatedSlot);
}