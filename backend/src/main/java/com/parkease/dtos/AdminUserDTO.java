package com.parkease.dtos;

public class AdminUserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private Boolean isActive;
    private String createdAt;

    public Long getId() { return id; }
    public void setId(Long v) { this.id = v; }
    public String getFullName() { return fullName; }
    public void setFullName(String v) { this.fullName = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String v) { this.phoneNumber = v; }
    public String getRole() { return role; }
    public void setRole(String v) { this.role = v; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean v) { this.isActive = v; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String v) { this.createdAt = v; }
}