package com.parkease.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingSlot;

public interface OwnerSlotService {
    ParkingSlot addSlot(ParkingSlotRequestDTO slot);
  
	List<ParkingSlot> getOwnerSlots();
	Boolean deleteSlot(Long slotId);

	ParkingSlot updateSlot(Long slotId, ParkingSlotRequestDTO updatedSlot);
}
