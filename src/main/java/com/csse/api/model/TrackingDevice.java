package com.csse.api.model;

import com.csse.api.enums.TrackingDeviceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrackingDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private float currentWasteLevel;

    @Enumerated(EnumType.STRING)
    private TrackingDeviceStatus status;

    @OneToOne(mappedBy = "trackingDevice")
    private Bin bin;
}
