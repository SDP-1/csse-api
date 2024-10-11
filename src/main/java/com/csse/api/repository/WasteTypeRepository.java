package com.csse.api.repository;

import com.csse.api.model.WasteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteTypeRepository extends JpaRepository<WasteType, Long> {
}
