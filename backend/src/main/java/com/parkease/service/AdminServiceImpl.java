package com.parkease.service;

import com.parkease.dtos.*;
import com.parkease.models.*;
import com.parkease.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepo;
    private final BookingRepo bookingRepo;
    private final ParkingSlotRepo slotRepo;

    private AdminUserDTO toUserDTO(User u) {
        AdminUserDTO dto = new AdminUserDTO();
        dto.setId(u.getId());
        dto.setFullName(u.getFullName());
        dto.setEmail(u.getEmail());
        dto.setPhoneNumber(u.getPhoneNumber());
        dto.setRole(u.getRole() != null ? u.getRole().name() : "USER");
        dto.setIsActive(u.getIsActive() != null ? u.getIsActive() : true);
        dto.setCreatedAt(u.getCreatedAt() != null ? u.getCreatedAt().toString() : "");
        return dto;
    }

    private AdminBookingDTO toBookingDTO(Booking b) {
        AdminBookingDTO dto = new AdminBookingDTO();
        dto.setId(b.getId());
        dto.setSlotNumber(b.getParkingSlot().getSlotNumber());
        dto.setVehicleType(b.getParkingSlot().getVehicleType() != null
            ? b.getParkingSlot().getVehicleType().name() : "CAR");
        dto.setUserName(b.getUser().getFullName());
        dto.setUserEmail(b.getUser().getEmail());
        dto.setOwnerName(b.getParkingSlot().getParkingArea() != null
            ? b.getParkingSlot().getParkingArea().getLotName() : "N/A");
        dto.setStartTime(b.getStartTime() != null ? b.getStartTime().toString() : "");
        dto.setEndTime(b.getEndTime() != null ? b.getEndTime().toString() : "");
        dto.setStatus(b.getStatus() != null ? b.getStatus().name() : "");
        long hours = b.getStartTime() != null && b.getEndTime() != null
            ? ChronoUnit.HOURS.between(b.getStartTime(), b.getEndTime()) : 0;
        if (hours < 1) hours = 1;
        dto.setTotalCost(hours * b.getParkingSlot().getPricePerHour());
        return dto;
    }

    private AdminSlotDTO toSlotDTO(ParkingSlot s) {
        AdminSlotDTO dto = new AdminSlotDTO();
        dto.setId(s.getId());
        dto.setSlotNumber(s.getSlotNumber());
        dto.setVehicleType(s.getVehicleType() != null ? s.getVehicleType().name() : "CAR");
        dto.setPricePerHour(s.getPricePerHour());
        dto.setIsAvailable(s.getIsAvailable());
        dto.setOwnerName(s.getParkingArea() != null ? s.getParkingArea().getLotName() : "N/A");
        dto.setOwnerEmail("");
        return dto;
    }

    @Override
    public AdminStatsDTO getStats() {
        AdminStatsDTO dto = new AdminStatsDTO();
        List<User> allUsers = userRepo.findAll();
        List<Booking> allBookings = bookingRepo.findAll();
        List<ParkingSlot> allSlots = slotRepo.findAll();
        dto.setTotalUsers(allUsers.stream().filter(u -> u.getRole() == Role.USER).count());
        dto.setTotalOwners(allUsers.stream().filter(u -> u.getRole() == Role.OWNER).count());
        dto.setTotalSlots(allSlots.size());
        dto.setAvailableSlots(allSlots.stream().filter(s -> Boolean.TRUE.equals(s.getIsAvailable())).count());
        dto.setTotalBookings(allBookings.size());
        dto.setActiveBookings(allBookings.stream().filter(b -> b.getStatus() == BookingStatus.BOOKED).count());
        dto.setCancelledBookings(allBookings.stream().filter(b -> b.getStatus() == BookingStatus.CANCELLED).count());
        dto.setTotalRevenue(allBookings.stream()
            .filter(b -> b.getStatus() != BookingStatus.CANCELLED)
            .mapToDouble(b -> {
                long hours = b.getStartTime() != null && b.getEndTime() != null
                    ? ChronoUnit.HOURS.between(b.getStartTime(), b.getEndTime()) : 0;
                if (hours < 1) hours = 1;
                return hours * b.getParkingSlot().getPricePerHour();
            }).sum());
        return dto;
    }

    @Override
    public List<AdminUserDTO> getAllUsers() {
        return userRepo.findAll().stream()
            .filter(u -> u.getRole() == Role.USER)
            .map(this::toUserDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AdminUserDTO> getAllOwners() {
        return userRepo.findAll().stream()
            .filter(u -> u.getRole() == Role.OWNER)
            .map(this::toUserDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AdminBookingDTO> getAllBookings() {
        return bookingRepo.findAll().stream()
            .map(this::toBookingDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AdminSlotDTO> getAllSlots() {
        return slotRepo.findAll().stream()
            .map(this::toSlotDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> toggleUserStatus(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        boolean newStatus = !Boolean.TRUE.equals(user.getIsActive());
        user.setIsActive(newStatus);
        userRepo.save(user);
        return Map.of("message", "User " + (newStatus ? "activated" : "deactivated") + " successfully",
                      "status", newStatus ? "active" : "inactive");
    }

    @Override
    @Transactional
    public Map<String, String> deleteUser(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Delete user's bookings first to avoid foreign key constraint
        bookingRepo.deleteByUserId(userId);

        // Now safely delete the user
        userRepo.delete(user);

        return Map.of("message", "User deleted successfully");
    }
}