package com.parkease.controllers;

import com.parkease.dtos.BookingRequestDTO;
import com.parkease.dtos.BookingResponseDTO;
import com.parkease.service.UserBookingService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class BookingController {

    private final UserBookingService bookingService;

    @PostMapping("/book-slot")
    public ResponseEntity<BookingResponseDTO> bookSlot(
            @RequestBody BookingRequestDTO request) {

        return ResponseEntity.ok(bookingService.bookSlot(request));
    }
    
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getMyBookings() {

        return ResponseEntity.ok(bookingService.getMyBookings());
    }
    
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {

        bookingService.cancelBooking(id);

        return ResponseEntity.ok("Booking cancelled successfully");
    }
}