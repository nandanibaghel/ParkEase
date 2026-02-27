package com.parkease.controllers;

import com.parkease.dtos.BookingRequestDTO;
import com.parkease.dtos.BookingResponseDTO;
import com.parkease.models.ParkingSlot;
import com.parkease.service.UserBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserBookingService userBookingService;

    @GetMapping("/slots")
    public ResponseEntity<List<ParkingSlot>> getAvailableSlots() {
        return ResponseEntity.ok(userBookingService.getAvailableSlots());
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> bookSlot(
            @RequestBody BookingRequestDTO request) {
        return ResponseEntity.ok(userBookingService.bookSlot(request));
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getMyBookings() {
        return ResponseEntity.ok(userBookingService.getMyBookings());
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(userBookingService.cancelBooking(id));
    }
}