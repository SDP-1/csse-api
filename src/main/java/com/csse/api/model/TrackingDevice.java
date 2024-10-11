package com.csse.api.model;

import com.csse.api.enums.BinStatus;
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

    @Enumerated(EnumType.STRING)
    private BinStatus binStatus;

    @OneToOne(mappedBy = "trackingDevice")
    private Bin bin;

    public void updateBinStatus() {
        if (this.currentWasteLevel == 0) {
            this.binStatus = BinStatus.EMPTY;
        } else if (this.currentWasteLevel > 0 && this.currentWasteLevel <= 50) {
            this.binStatus = BinStatus.HALF_FULL;
        } else if (this.currentWasteLevel > 50 && this.currentWasteLevel <= 80) {
            this.binStatus = BinStatus.EIGHTY_PERCENT;
        } else {
            this.binStatus = BinStatus.OVERFLOWING;
        }
    }
}
