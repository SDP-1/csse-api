package com.csse.api.model;

import com.csse.api.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleTypeRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "special_waste_request_id")
    private SpecialWasteRequest specialWasteRequest;
}
