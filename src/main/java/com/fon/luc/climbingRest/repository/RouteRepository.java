package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.Route;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query(value = "SELECT DISTINCT r.* FROM route r "
            ,nativeQuery = true)
    List<Route> getFilterRoute(Pageable pageable);

    @Query(value = "SELECT COUNT (DISTINCT r.route_id) FROM route r",
            nativeQuery = true)
    Long getFilterRouteCount();

    Route getRouteByName(String name);

    Route getRouteById(Long id);
}
