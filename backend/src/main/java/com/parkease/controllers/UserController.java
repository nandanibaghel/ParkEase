package com.parkease.controllers;

import com.parkease.dtos.*;
import com.parkease.models.ParkingSlot;
import com.parkease.service.UserBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "👤 User - Operations", description = "User endpoints for booking and profile management")
public class UserController {

    private final UserBookingService userBookingService;

    @GetMapping("/slots")
    public ResponseEntity<List<ParkingSlot>> getAvailableSlots() {
        return ResponseEntity.ok(userBookingService.getAvailableSlots());
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> bookSlot(@RequestBody BookingRequestDTO request) {
        return ResponseEntity.ok(userBookingService.bookSlot(request));
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getMyBookings() {
        return ResponseEntity.ok(userBookingService.getMyBookings());
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Map<String,String>> cancelBooking(@PathVariable Long id) {
        String result = userBookingService.cancelBooking(id);
        return ResponseEntity.ok(Map.of("message", result));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile() {
        return ResponseEntity.ok(userBookingService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@RequestBody ProfileUpdateDTO request) {
        return ResponseEntity.ok(userBookingService.updateProfile(request));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Map<String,String>> changePassword(@RequestBody ChangePasswordDTO request) {
        String result = userBookingService.changePassword(request);
        return ResponseEntity.ok(Map.of("message", result));
    }
}