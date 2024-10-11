package com.csse.api.repository;

import com.csse.api.model.VehicleTypeRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRequirementRepository extends JpaRepository<VehicleTypeRequirement, Long> {
}
