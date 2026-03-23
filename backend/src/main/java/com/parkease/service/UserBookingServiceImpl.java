package com.parkease.service;

import com.parkease.dtos.*;
import com.parkease.models.*;
import com.parkease.repository.BookingRepo;
import com.parkease.repository.ParkingSlotRepo;
import com.parkease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

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
        dto.setVehicleType(b.getParkingSlot().getVehicleType() != null
            ? b.getParkingSlot().getVehicleType().name() : "CAR");
        dto.setPricePerHour(b.getParkingSlot().getPricePerHour());
        dto.setStartTime(b.getStartTime());
        dto.setEndTime(b.getEndTime());
        dto.setStatus(b.getStatus());
        long hours = ChronoUnit.HOURS.between(b.getStartTime(), b.getEndTime());
        if (hours < 1) hours = 1;
        dto.setTotalCost(hours * b.getParkingSlot().getPricePerHour());
        return dto;
    }

    private ProfileResponseDTO toProfileDTO(User u) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(u.getId());
        dto.setFullName(u.getFullName());
        dto.setEmail(u.getEmail());
        dto.setPhoneNumber(u.getPhoneNumber());
        dto.setRole(u.getRole() != null ? u.getRole().name() : "USER");
        dto.setCreatedAt(u.getCreatedAt() != null ? u.getCreatedAt().toString() : "");
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

        LocalDateTime start = request.getStartTime();
        LocalDateTime end   = request.getEndTime();

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

    @Override
    public ProfileResponseDTO getProfile() {
        return toProfileDTO(getCurrentUser());
    }

    @Override
    public ProfileResponseDTO updateProfile(ProfileUpdateDTO request) {
        User user = getCurrentUser();
        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            user.setFullName(request.getFullName());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        return toProfileDTO(userRepo.save(user));
    }

    @Override
    public String changePassword(ChangePasswordDTO request) {
        User user = getCurrentUser();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        if (request.getNewPassword() == null || request.getNewPassword().length() < 6) {
            throw new RuntimeException("New password must be at least 6 characters");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);
        return "Password changed successfully";
    }
}