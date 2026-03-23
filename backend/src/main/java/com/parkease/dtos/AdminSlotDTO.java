package com.parkease.dtos;

public class AdminSlotDTO {
    private Long id;
    private String slotNumber;
    private String vehicleType;
    private Double pricePerHour;
    private Boolean isAvailable;
    private String ownerName;
    private String ownerEmail;

    public Long getId() { return id; }
    public void setId(Long v) { this.id = v; }
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String v) { this.slotNumber = v; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String v) { this.vehicleType = v; }
    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double v) { this.pricePerHour = v; }
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean v) { this.isAvailable = v; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String v) { this.ownerName = v; }
    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String v) { this.ownerEmail = v; }
}













