package com.parkease.service;

import com.parkease.dtos.*;
import java.util.List;
import java.util.Map;

public interface AdminService {
    AdminStatsDTO getStats();
    List<AdminUserDTO> getAllUsers();
    List<AdminUserDTO> getAllOwners();
    List<AdminBookingDTO> getAllBookings();
    List<AdminSlotDTO> getAllSlots();
    Map<String, String> toggleUserStatus(Long userId);
    Map<String, String> deleteUser(Long userId);
}


















