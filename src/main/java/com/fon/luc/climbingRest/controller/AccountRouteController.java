package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.formData.AddAccountRoute;
import com.fon.luc.climbingRest.formData.AddAccountRouteCompetition;
import com.fon.luc.climbingRest.model.*;
import com.fon.luc.climbingRest.service.AccountRouteService;
import com.fon.luc.climbingRest.service.AccountService;
import com.fon.luc.climbingRest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/accountroutes")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountRouteController {

    @Autowired
    AccountRouteService accountRouteService;
    @Autowired
    AccountService accountService;
    @Autowired
    RouteService routeService;

    @PostMapping
    public AccountRoute createAccountRoute(@Valid @RequestBody AddAccountRoute addAccountRoute) {
        AccountRoute complete;
        Account account = accountService.findByEmail(addAccountRoute.email);
        Route route = routeService.findById(addAccountRoute.routeId);
        if (addAccountRoute.zone == 0) {
            complete = new AccountRoute(account, route);
        }
        else {
            int zone = addAccountRoute.zone;
            complete = new AccountRoute(account, route, zone);
        }
        return accountRouteService.createAccountRoute(complete);
    }

    @PostMapping(value = "/competition")
    public AccountRoute createAccountRouteCompetition(@Valid @RequestBody AddAccountRouteCompetition addAccountRouteCompetition) {
        AccountRoute complete;
        Account account = accountService.findByEmail(addAccountRouteCompetition.email);
        Route route = routeService.findById(addAccountRouteCompetition.routeId);
        if (addAccountRouteCompetition.zone == 0) {
            complete = new AccountRoute(account, route);
        }
        else {
            int zone = addAccountRouteCompetition.zone;
            complete = new AccountRoute(account, route, zone);
        }
        return accountRouteService.createAccountRouteCompetition(complete, addAccountRouteCompetition.competitionId);
    }

    @RequestMapping(value= "/accountroutecount/{email}", method = RequestMethod.GET)
    public int getAccountRouteCountByEmail(@PathVariable("email") String email) {
        return accountRouteService.countRoutesByAccountEmail(email);
    }

}
