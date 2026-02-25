package com.parkease.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import com.parkease.models.Booking;

public interface OwnerBookingService {
    List<Booking> getOwnerBookings();
}