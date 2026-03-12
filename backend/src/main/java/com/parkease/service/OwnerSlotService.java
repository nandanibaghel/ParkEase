package com.parkease.service;

import java.util.List;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.dtos.ParkingSlotResponseDTO;
import com.parkease.models.ParkingSlot;

public interface OwnerSlotService {

    ParkingSlotResponseDTO addSlot(ParkingSlotRequestDTO slot);

    ParkingSlotResponseDTO updateSlot(Long slotId, ParkingSlotRequestDTO updatedSlot);

    List<ParkingSlotResponseDTO> getOwnerSlots(Long areaId);

    Boolean deleteSlot(Long slotId);
}
