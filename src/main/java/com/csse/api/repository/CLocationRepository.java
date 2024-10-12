package com.csse.api.repository;

import com.csse.api.model.CLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CLocationRepository extends JpaRepository<CLocation, Long> {
    // You can define custom query methods here if needed
}

