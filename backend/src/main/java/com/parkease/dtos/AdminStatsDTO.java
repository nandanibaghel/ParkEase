package com.parkease.dtos;

public class AdminStatsDTO {
    private long totalUsers;
    private long totalOwners;
    private long totalSlots;
    private long availableSlots;
    private long totalBookings;
    private long activeBookings;
    private long cancelledBookings;
    private double totalRevenue;

    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long v) { this.totalUsers = v; }
    public long getTotalOwners() { return totalOwners; }
    public void setTotalOwners(long v) { this.totalOwners = v; }
    public long getTotalSlots() { return totalSlots; }
    public void setTotalSlots(long v) { this.totalSlots = v; }
    public long getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(long v) { this.availableSlots = v; }
    public long getTotalBookings() { return totalBookings; }
    public void setTotalBookings(long v) { this.totalBookings = v; }
    public long getActiveBookings() { return activeBookings; }
    public void setActiveBookings(long v) { this.activeBookings = v; }
    public long getCancelledBookings() { return cancelledBookings; }
    public void setCancelledBookings(long v) { this.cancelledBookings = v; }
    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double v) { this.totalRevenue = v; }
}









