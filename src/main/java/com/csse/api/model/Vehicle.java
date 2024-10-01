package com.csse.api.model;

import com.csse.api.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Unique identifier for each vehicle
    private String registrationNumber; // Vehicle registration number
    private VehicleType type; // Type of vehicle (SM, MD, LG, XL)
    private String model; // Model of the vehicle
    private int capacity; // Capacity of the vehicle in kg
    private double priceToRent; // Price to rent the vehicle

    @OneToMany(mappedBy = "vehicle")
    private List<CollectorAssignment> assignments; // List of assignments associated with this vehicle
}
