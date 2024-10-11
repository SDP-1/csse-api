package com.csse.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialWasteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "waste_type_id")
    private WasteType wasteType;

    private String location;

    @OneToMany(mappedBy = "specialWasteRequest", cascade = CascadeType.ALL)
    private List<VehicleTypeRequirement> requiredVehicleTypes;

    @ManyToMany
    @JoinTable(
            name = "garbage_collector_special_waste_request",
            joinColumns = @JoinColumn(name = "special_waste_request_id"),
            inverseJoinColumns = @JoinColumn(name = "garbage_collector_id")
    )
    private List<GarbageCollector> garbageCollectors;
}
