package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.model.AccountRoute;
import com.fon.luc.climbingRest.service.AccountCompetitionService;
import com.fon.luc.climbingRest.service.AccountRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class PostWebController {

//    @Autowired
//    AccountCompetitionService accountCompetitionService;
//    @Autowired
//    AccountRouteService accountRouteService;

//    @MessageMapping("/app/create")
//    @SendTo("/topic/create")
//    public AccountRoute save(AccountRoute accountRoute) {
//        return accountRouteService.createAccountRoute(accountRoute);
//    }

    @MessageExceptionHandler
    @SendToUser("/topic/error")
    public String handleException(IllegalAccessError ex) {
        return "The given author was not found";
    }

}
