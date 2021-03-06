package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.formData.RouteFilter;
import com.fon.luc.climbingRest.model.Route;
import com.fon.luc.climbingRest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/routes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RouteController {

    @Autowired
    RouteService routeService;

    @RequestMapping(value = "/getfilterroutecount", method = RequestMethod.GET)
    public ResponseEntity<Long> getFilterRouteCount() {
        return new ResponseEntity<Long>(routeService.getFilterRouteCount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getfilterroute", method = RequestMethod.POST)
    public ResponseEntity<Object> getFilterRoute(@RequestBody RouteFilter filter) {
        return new ResponseEntity<Object>(routeService.getFilterRoute(filter), HttpStatus.OK);
    }

    @PostMapping("/{name}")
    public Route createRoute(@PathVariable("name") String name) {
        Route route = new Route(name);
        return routeService.createRoute(route);
    }

    @PostMapping("/saveroute")
    public Route createRoute(@RequestBody Route route) {
        return route;
    }


}
