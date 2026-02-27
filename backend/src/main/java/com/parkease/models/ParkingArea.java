package com.parkease.models;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ParkingArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lotName;
    private String address;
    private String city;
    private String pincode;
    private int totalSlots;

    @OneToMany(mappedBy = "parkingArea", cascade = CascadeType.ALL)
    private List<ParkingSlot> slots = new ArrayList<>();
}
