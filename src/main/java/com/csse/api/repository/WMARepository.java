package com.csse.api.repository;

import com.csse.api.model.WMA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WMARepository extends JpaRepository<WMA, Long> { // Change to Long
    // Additional query methods (if needed) can be defined here
}
