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
@ToString
public class GarbageCollector extends User {
    private String collectorId;
    private String vehicleRegNo;
    private VehicleType vehicleType;
    private String model;
    private String currentStatus;
    private String currentLocation;

    @ManyToOne
    @JoinColumn(name = "wma_id", nullable = false)
    private WMA wma;

    @OneToMany(mappedBy = "collector", cascade = CascadeType.ALL)
    private List<CollectorAssignment> assignments;

    @ManyToMany(mappedBy = "garbageCollectors")
    private List<SpecialWasteRequest> specialWasteRequests;

    public GarbageCollector(long id) {
        this.id = id;
    }
}
