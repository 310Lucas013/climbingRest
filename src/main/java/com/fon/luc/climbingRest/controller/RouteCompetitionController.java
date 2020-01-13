package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.model.Route;
import com.fon.luc.climbingRest.model.RouteCompetition;
import com.fon.luc.climbingRest.service.RouteCompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/routecompetitions")
@CrossOrigin(origins = "http://localhost:4200")
public class RouteCompetitionController {

    @Autowired
    RouteCompetitionService routeCompetitionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public List<RouteCompetition> getRouteCompetitions(@PathVariable("id") Long id) {
        List<RouteCompetition> returnedValue = this.routeCompetitionService.getRouteCompetitions(id);
        System.out.println(returnedValue);
        return returnedValue;
    }
}
