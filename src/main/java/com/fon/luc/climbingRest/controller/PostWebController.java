package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.messages.AddAccountRouteMessage;
import com.fon.luc.climbingRest.messages.CreateAccountRouteMessage;
import com.fon.luc.climbingRest.model.Account;
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
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/socket")
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

    @MessageMapping("/send/message")
//    @SendTo("/queue")
    public void save(AddAccountRouteMessage message) {
        Account account = accountService.findByEmail(message.getEmail());
        Route route = routeService.findById(message.getRouteId());
        System.out.println("Method Called");
        this.template.convertAndSend("/topic/queue", new AccountRoute(account, route, message.getZone()));
        // return gson.toJson(accountRouteService.createAccountRoute(new AccountRoute(account, route, message.getZone())), AccountRoute.class);
    }

    @MessageExceptionHandler
    @SendToUser("/queue/error")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
