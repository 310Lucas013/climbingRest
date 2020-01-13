package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.enums.Group;
import com.fon.luc.climbingRest.formData.AddAccountCompetition;
import com.fon.luc.climbingRest.formData.AddAccountRoute;
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
        System.out.println(addAccountRoute.toString());
        AccountRoute complete;
        Account account = accountService.findByEmail(addAccountRoute.email);
        System.out.println(account);
        Route route = routeService.findById(addAccountRoute.routeId);
        System.out.println(route.toString());
        if (addAccountRoute.zone == 0) {
            complete = new AccountRoute(account, route);
        }
        else {
            int zone = addAccountRoute.zone;
            complete = new AccountRoute(account, route, zone);
        }
        return accountRouteService.createAccountRoute(complete);
    }

}
