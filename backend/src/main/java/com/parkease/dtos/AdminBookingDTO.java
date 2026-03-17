package com.parkease.dtos;

public class AdminBookingDTO {
    private Long id;
    private String slotNumber;
    private String vehicleType;
    private String userName;
    private String userEmail;
    private String ownerName;
    private String startTime;
    private String endTime;
    private String status;
    private Double totalCost;

    public Long getId() { return id; }
    public void setId(Long v) { this.id = v; }
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String v) { this.slotNumber = v; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String v) { this.vehicleType = v; }
    public String getUserName() { return userName; }
    public void setUserName(String v) { this.userName = v; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String v) { this.userEmail = v; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String v) { this.ownerName = v; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String v) { this.startTime = v; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String v) { this.endTime = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double v) { this.totalCost = v; }
}