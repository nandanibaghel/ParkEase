package com.parkease.controllers;

import com.parkease.dtos.*;
import com.parkease.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "👑 Admin - Management", description = "Admin endpoints for system-wide management")
public class AdminController {

    private final AdminService adminService;

    // ── Stats ──────────────────────────────────────
    @GetMapping("/stats")
    public ResponseEntity<AdminStatsDTO> getStats() {
        return ResponseEntity.ok(adminService.getStats());
    }

    // ── Users ──────────────────────────────────────
    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/owners")
    public ResponseEntity<List<AdminUserDTO>> getAllOwners() {
        return ResponseEntity.ok(adminService.getAllOwners());
    }

    @PutMapping("/users/{id}/toggle")
    public ResponseEntity<Map<String, String>> toggleUserStatus(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.toggleUserStatus(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.deleteUser(id));
    }

    // ── Bookings ───────────────────────────────────
    @GetMapping("/bookings")
    public ResponseEntity<List<AdminBookingDTO>> getAllBookings() {
        return ResponseEntity.ok(adminService.getAllBookings());
    }

    // ── Slots ──────────────────────────────────────
    @GetMapping("/slots")
    public ResponseEntity<List<AdminSlotDTO>> getAllSlots() {
        return ResponseEntity.ok(adminService.getAllSlots());
    }
}







