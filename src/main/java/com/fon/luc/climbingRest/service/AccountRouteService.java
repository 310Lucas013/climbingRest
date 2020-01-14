package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.formData.Scoreboard;
import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.AccountRoute;
import com.fon.luc.climbingRest.repository.AccountRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class AccountRouteService {

    @Autowired
    AccountRouteRepository accountRouteRepository;

    public AccountRoute createAccountRoute(AccountRoute accountRoute) {
        updateScoreboard(accountRoute);
        return accountRouteRepository.save(accountRoute);
    }

    public int countRoutesByAccountEmail(String email) {
        return accountRouteRepository.countAccountRouteByAccount_Email(email);
    }

    public void updateScoreboard(AccountRoute accountRoute) {
        Scoreboard scoreboard = new Scoreboard();
        // GetAllAccountRoutes
    }



}
