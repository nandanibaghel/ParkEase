package com.parkease.service;

import com.parkease.dtos.BookingRequestDTO;
import com.parkease.dtos.BookingResponseDTO;
import com.parkease.models.ParkingSlot;
import java.util.List;

public interface UserBookingService {
	BookingResponseDTO bookSlot(BookingRequestDTO request);
//    List<ParkingSlot> getAvailableSlots();
    List<BookingResponseDTO> getMyBookings();
    void cancelBooking(Long bookingId);
}