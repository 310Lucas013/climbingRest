package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.RouteCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface RouteCompetitionRepository extends JpaRepository<RouteCompetition, Long> {
    List<RouteCompetition> findByCompetition_Id(Long id);

    RouteCompetition findRouteCompetitionByCompetition_IdAndRoute_Id(Long competitionId, Long routeId);
}
