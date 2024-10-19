package com.csse.api.dto.tracking_device;

import com.csse.api.enums.BinStatus;
import com.csse.api.enums.TrackingDeviceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingDeviceRequestDTO {
    private String name;
    private String type;
    private float currentWasteLevel;
    private TrackingDeviceStatus status;
    private BinStatus binStatus;
}
