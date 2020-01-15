package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.model.RouteCompetition;
import com.fon.luc.climbingRest.repository.RouteCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class RouteCompetitionService {

    @Autowired
    RouteCompetitionRepository routeCompetitionRepository;

    public List<RouteCompetition> getRouteCompetitions(Long id) {
        List<RouteCompetition> routeCompetition = this.routeCompetitionRepository.findByCompetition_Id(id);
        System.out.println(routeCompetition);
        return routeCompetition;
    }

    public RouteCompetition getRouteCompetitionByRouteId(Long id) {
        return this.routeCompetitionRepository.findRouteCompetitionByRoute_Id(id);
    }
}
