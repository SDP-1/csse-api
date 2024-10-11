package com.csse.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "waste_type_id", nullable = false)
    private WasteType wasteType;

    @OneToMany(mappedBy = "bin", cascade = CascadeType.ALL)
    private List<CollectionRecord> collectionRecords;

    @OneToOne
    @JoinColumn(name = "tracking_device_id")
    private TrackingDevice trackingDevice;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "bin_type_id", nullable = false)
    private BinTypes binType;
}
