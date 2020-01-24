package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.Competition;
import com.fon.luc.climbingRest.model.Route;
import com.fon.luc.climbingRest.model.RouteCompetition;
import com.fon.luc.climbingRest.service.CompetitionService;
import com.fon.luc.climbingRest.service.RouteCompetitionService;
import com.fon.luc.climbingRest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/routecompetitions")
@CrossOrigin(origins = "http://localhost:4200")
public class RouteCompetitionController {

    @Autowired
    RouteCompetitionService routeCompetitionService;
    @Autowired
    RouteService routeService;
    @Autowired
    CompetitionService competitionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public List<RouteCompetition> getRouteCompetitions(@PathVariable("id") Long id) {
        List<RouteCompetition> returnedValue = this.routeCompetitionService.getRouteCompetitions(id);
        return returnedValue;
    }

    @PostMapping("/{routename}/{competitionname}")
    public RouteCompetition createRouteCompetion(@PathVariable("routename") String routeName, @PathVariable("competitionname") String competitionName) {
        Route route = routeService.findByName(routeName);
        Competition competition = competitionService.findByName(competitionName);
        RouteCompetition routeCompetition = new RouteCompetition(route, competition);
        return routeCompetitionService.createRouteCompetition(routeCompetition);
    }
}
