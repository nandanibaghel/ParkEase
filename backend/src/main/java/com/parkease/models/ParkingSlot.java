package com.parkease.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private Double pricePerHour;

    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "parking_area_id", nullable = false)
    @JsonBackReference  // ← This fixes the circular reference
    private ParkingArea parkingArea;
}