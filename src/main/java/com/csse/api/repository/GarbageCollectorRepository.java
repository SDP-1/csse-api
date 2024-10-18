package com.csse.api.repository;

import com.csse.api.model.GarbageCollector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarbageCollectorRepository extends JpaRepository<GarbageCollector, Long> {
}
