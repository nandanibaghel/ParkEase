package com.parkease.service;

import com.parkease.dtos.BookingRequestDTO;
import com.parkease.dtos.BookingResponseDTO;
import com.parkease.models.*;
import com.parkease.repository.BookingRepo;
import com.parkease.repository.ParkingSlotRepo;
import com.parkease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserBookingServiceImpl implements UserBookingService {

    private final BookingRepo bookingRepo;
    private final ParkingSlotRepo slotRepo;
    private final UserRepository userRepo;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                           .getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private BookingResponseDTO toDTO(Booking b) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(b.getId());
        dto.setSlotNumber(b.getParkingSlot().getSlotNumber());
        dto.setPricePerHour(b.getParkingSlot().getPricePerHour());
        dto.setStartTime(b.getStartTime());
        dto.setEndTime(b.getEndTime());
        dto.setStatus(b.getStatus());

        long hours = ChronoUnit.HOURS.between(b.getStartTime(), b.getEndTime());
        dto.setTotalCost(hours * b.getParkingSlot().getPricePerHour());

        return dto;
    }

    @Override
    public List<ParkingSlot> getAvailableSlots() {
        return slotRepo.findByIsAvailableTrue();
    }

    @Override
    public BookingResponseDTO bookSlot(BookingRequestDTO request) {
        User user = getCurrentUser();

        ParkingSlot slot = slotRepo.findById(request.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (!slot.getIsAvailable()) {
            throw new RuntimeException("Slot is already booked");
        }

        LocalDateTime start = LocalDateTime.parse(request.getStartTime());
        LocalDateTime end   = LocalDateTime.parse(request.getEndTime());

        if (!end.isAfter(start)) {
            throw new RuntimeException("End time must be after start time");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setParkingSlot(slot);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setStatus(BookingStatus.BOOKED);

        slot.setIsAvailable(false);
        slotRepo.save(slot);

        Booking saved = bookingRepo.save(booking);
        return toDTO(saved);
    }

    @Override
    public List<BookingResponseDTO> getMyBookings() {
        User user = getCurrentUser();
        return bookingRepo.findByUserId(user.getId())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String cancelBooking(Long bookingId) {
        User user = getCurrentUser();

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only cancel your own bookings");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);

        ParkingSlot slot = booking.getParkingSlot();
        slot.setIsAvailable(true);
        slotRepo.save(slot);

        return "Booking cancelled successfully";
    }
}