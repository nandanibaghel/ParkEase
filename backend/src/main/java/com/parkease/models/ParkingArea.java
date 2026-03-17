package com.parkease.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"password","bookings","slots"})
    private User owner;

    @OneToMany(mappedBy = "parkingArea", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JsonManagedReference
    private List<ParkingSlot> slots = new ArrayList<>();
}