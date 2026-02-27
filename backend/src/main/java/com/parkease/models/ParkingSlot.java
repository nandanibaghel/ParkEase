package com.parkease.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType; // CAR, BIKE

    @Column(nullable = false)
    private Double pricePerHour;

    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "parking_area_id", nullable = false)
    private ParkingArea parkingArea;
}
