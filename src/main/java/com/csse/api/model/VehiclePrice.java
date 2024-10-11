package com.csse.api.model;

import com.csse.api.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehiclePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType; // Type of vehicle

    private double price; // Price for the vehicle type

    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = false)
    private GarbageCollector garbageCollector; // Link to the garbage collector managing this price
}
