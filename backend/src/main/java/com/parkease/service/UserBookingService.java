package com.parkease.service;

import com.parkease.dtos.*;
import com.parkease.models.ParkingSlot;
import java.util.List;

public interface UserBookingService {
    List<ParkingSlot> getAvailableSlots();
    BookingResponseDTO bookSlot(BookingRequestDTO request);
    List<BookingResponseDTO> getMyBookings();
    String cancelBooking(Long bookingId);

    // Profile
    ProfileResponseDTO getProfile();
    ProfileResponseDTO updateProfile(ProfileUpdateDTO request);
    String changePassword(ChangePasswordDTO request);
}
