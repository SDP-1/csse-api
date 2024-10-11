package com.csse.api.repository;

import com.csse.api.model.TrackingDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingDeviceRepository extends JpaRepository<TrackingDevice, Long> {
}
