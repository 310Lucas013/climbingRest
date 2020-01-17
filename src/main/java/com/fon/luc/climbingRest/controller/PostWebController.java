package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.messages.CreateAccountRouteMessage;
import com.fon.luc.climbingRest.model.AccountRoute;
import com.fon.luc.climbingRest.service.AccountCompetitionService;
import com.fon.luc.climbingRest.service.AccountRouteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class PostWebController {

    @Autowired
    AccountCompetitionService accountCompetitionService;
    @Autowired
    AccountRouteService accountRouteService;
    private Gson gson = new Gson();

    @MessageMapping("/message")
    @SendTo("/queue/reply")
    public String save(@Payload CreateAccountRouteMessage message) {
        return gson.toJson(accountRouteService.createAccountRoute(new AccountRoute(message.getAccount(), message.getRoute(), message.getMaxZone())), AccountRoute.class);
    }

    @MessageExceptionHandler
    @SendToUser("/queue/error")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
