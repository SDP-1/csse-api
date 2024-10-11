package com.csse.api.repository;

import com.csse.api.model.BinTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinTypesRepository extends JpaRepository<BinTypes, Long> {

}
