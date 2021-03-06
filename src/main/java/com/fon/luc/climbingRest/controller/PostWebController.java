package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.messages.AddAccountRouteCompetitionMessage;
import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.AccountCompetition;
import com.fon.luc.climbingRest.model.AccountRoute;
import com.fon.luc.climbingRest.model.Route;
import com.fon.luc.climbingRest.service.AccountCompetitionService;
import com.fon.luc.climbingRest.service.AccountRouteService;
import com.fon.luc.climbingRest.service.AccountService;
import com.fon.luc.climbingRest.service.RouteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/socket")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PostWebController {

    private final SimpMessagingTemplate template;
    private Gson gson = new Gson();

    @Autowired
    AccountCompetitionService accountCompetitionService;
    @Autowired
    AccountRouteService accountRouteService;
    @Autowired
    AccountService accountService;
    @Autowired
    RouteService routeService;
    @Autowired
    PostWebController(SimpMessagingTemplate template) {this.template = template;}

    @MessageMapping("/send")
    @SendTo("/topic/send")
    public String save(@Payload String message) {
        AddAccountRouteCompetitionMessage arcm = new AddAccountRouteCompetitionMessage();
        arcm = gson.fromJson(message, AddAccountRouteCompetitionMessage.class);
        Account account = accountService.findByEmail(arcm.getEmail());
        Route route = routeService.findById(arcm.getRouteId());
        AccountRoute accountRoute = accountRouteService.createAccountRouteCompetition(new AccountRoute(account, route, arcm.getZone()), arcm.getCompetitionId());
        List<AccountCompetition> accountCompetitions = new ArrayList<AccountCompetition>();
        if (accountRoute != null) {
            accountCompetitions = accountCompetitionService.getAccountCompetitions(arcm.getCompetitionId());
        }
        String returnString = gson.toJson(accountCompetitions);
        return returnString;
    }

    @MessageExceptionHandler
    @SendTo("/topic/error")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
