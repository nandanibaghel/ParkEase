package com.parkease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.parkease.models.Booking;
import com.parkease.models.User;
import com.parkease.repository.BookingRepo;
import com.parkease.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerBookingServiceImpl implements OwnerBookingService {

	@Autowired
    private BookingRepo bookingRepo;
	@Autowired
    private UserRepository userRepo;

    @Override
    public List<Booking> getOwnerBookings() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User owner = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

//        return bookingRepo.findByParkingSlotOwnerId(owner.getId());
        return null;
    }
}