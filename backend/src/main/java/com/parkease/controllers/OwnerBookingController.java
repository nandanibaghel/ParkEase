package com.parkease.controllers;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkease.models.Booking;
import com.parkease.service.OwnerBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/owner/bookings")
@RequiredArgsConstructor
public class OwnerBookingController {

	@Autowired
    private OwnerBookingService ownerBookingService;
    

    @GetMapping
    public ResponseEntity<List<Booking>> getOwnerBookings(
            Authentication authentication) {

        return ResponseEntity.ok(
                ownerBookingService.getOwnerBookings()
        );
    }
}
