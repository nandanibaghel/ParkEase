<<<<<<< HEAD
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
=======
package com.parkease.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import com.parkease.dtos.ParkingSlotRequestDTO;
import com.parkease.models.ParkingSlot;

public interface OwnerSlotService {
    ParkingSlot addSlot(ParkingSlotRequestDTO slot);
    
    ParkingSlot updateSlot(Long slotId, ParkingSlot updatedSlot);
	List<ParkingSlot> getOwnerSlots();
}
>>>>>>> 94f254670e09825d37c4102ad2d3c7ce00be4e15
