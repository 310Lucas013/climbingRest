package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.model.RouteCompetition;
import com.fon.luc.climbingRest.repository.RouteCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class RouteCompetitionService {

    @Autowired
    RouteCompetitionRepository routeCompetitionRepository;

    public List<RouteCompetition> getRouteCompetitions(Long id) {
        List<RouteCompetition> routeCompetition = this.routeCompetitionRepository.findByCompetition_Id(id);
        return routeCompetition;
    }

    public RouteCompetition findRouteCompetitionByCompetition_IdAndRoute_Id(Long competitionId, Long routeId) {
        return this.routeCompetitionRepository.findRouteCompetitionByCompetition_IdAndRoute_Id(competitionId, routeId);
    }

    public RouteCompetition createRouteCompetition(RouteCompetition routeCompetition) {
        return routeCompetitionRepository.save(routeCompetition);
    }
}
