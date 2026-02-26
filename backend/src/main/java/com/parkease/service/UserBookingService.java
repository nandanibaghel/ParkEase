package com.parkease.service;

import com.parkease.dtos.BookingRequestDTO;
import com.parkease.dtos.BookingResponseDTO;
import com.parkease.models.ParkingSlot;
import java.util.List;

public interface UserBookingService {
    List<ParkingSlot> getAvailableSlots();
    BookingResponseDTO bookSlot(BookingRequestDTO request);
    List<BookingResponseDTO> getMyBookings();
    String cancelBooking(Long bookingId);
}