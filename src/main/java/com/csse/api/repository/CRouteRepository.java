package com.csse.api.repository;

import com.csse.api.model.CRoute;
import com.csse.api.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CRouteRepository extends JpaRepository<CRoute, Long> {
    Optional<CRoute> findByRouteName(String routeName);
}
