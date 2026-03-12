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
        dto.setStatus(b.getStatus().name());
        dto.setParkingAreaName(b.getParkingSlot().getParkingArea().getLotName());

        long hours = ChronoUnit.HOURS.between(b.getStartTime(), b.getEndTime());
        dto.setTotalCost(hours * b.getParkingSlot().getPricePerHour());

        return dto;
    }
    
    @Override
    public void cancelBooking(Long bookingId) {

        User user = getCurrentUser();

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Security check (user should cancel only his booking)
        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot cancel this booking");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        bookingRepo.save(booking);
    }
    
    @Override
    public List<BookingResponseDTO> getMyBookings() {

        User user = getCurrentUser();

        List<Booking> bookings = bookingRepo.findByUserId(user.getId());

        return bookings.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDTO bookSlot(BookingRequestDTO request) {

        User user = getCurrentUser(); // from JWT

        ParkingSlot slot = slotRepo.findById(request.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Check overlapping booking
        boolean alreadyBooked =
                bookingRepo.existsByParkingSlotIdAndStatusAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        slot.getId(),
                        BookingStatus.BOOKED,
                        request.getEndTime(),
                        request.getStartTime()
                );

        if (alreadyBooked) {
            throw new RuntimeException("Slot already booked for this time");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setParkingSlot(slot);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus(BookingStatus.BOOKED);

        Booking savedBooking = bookingRepo.save(booking);

     
        return toDTO(savedBooking);
    }
}