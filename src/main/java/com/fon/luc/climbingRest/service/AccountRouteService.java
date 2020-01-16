package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.formData.MaxRoute;
import com.fon.luc.climbingRest.formData.RouteZonePoint;
import com.fon.luc.climbingRest.formData.Scoreboard;
import com.fon.luc.climbingRest.model.*;
import com.fon.luc.climbingRest.repository.AccountCompetitionRepository;
import com.fon.luc.climbingRest.repository.AccountRouteRepository;
import com.fon.luc.climbingRest.repository.RouteCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class AccountRouteService {

    @Autowired
    AccountRouteRepository accountRouteRepository;
    @Autowired
    RouteCompetitionRepository routeCompetitionRepository;
    @Autowired
    AccountCompetitionRepository accountCompetitionRepository;

    public AccountRoute createAccountRoute(AccountRoute accountRoute) {
        AccountRoute returnable = accountRouteRepository.save(accountRoute);
        updateScoreboard(accountRoute);
        return returnable;
    }

    public int countRoutesByAccountEmail(String email) {
        return accountRouteRepository.countAccountRouteByAccount_Email(email);
    }

    public void updateScoreboard(AccountRoute accountRoute) {
        // Scoreboard
        List<Scoreboard> scoreboards = new ArrayList<Scoreboard>();
        // TemporaryAccount for checking points and routes that belong to a user.
        Account tempAccount = new Account();
        // TemporaryScoreboard for updating routes that belong to a user.
        Scoreboard tempScoreboard = new Scoreboard();
        // get competition by Route
        RouteCompetition routeCompetition = routeCompetitionRepository.findRouteCompetitionByRoute_Id(accountRoute.getRoute().getId());
        System.out.println(routeCompetition + "Row 39");
        // get all accountRouteBy Competition
        System.out.println(routeCompetition.getCompetition().getId());
        List<AccountRoute> accountRoutes = accountRouteRepository.getAccountRoutesByCompetitionId(routeCompetition.getCompetition().getId());
        System.out.println(accountRoutes);
        // Going through all the AccountRoutes that belong to the competition. --> working
        for (AccountRoute ar : accountRoutes) {
            // Checking if tempAccount is not the same as the current one.
            if (!tempAccount.equals(ar.getAccount())) {
                // Setting TempAccount to the new account.
                tempAccount = ar.getAccount();
                // Creating boolean value for checking if TempAccount is already in one of the scoreboards
                boolean alreadyInScoreboards = false;
                // Checking if TempAccount is already in one of the scoreboards.
                for (Scoreboard sb : scoreboards) {
                    if (sb.account == tempAccount) {
                        // TempAccount is already in one of the scoreboards.
                        alreadyInScoreboards = true;
                        tempScoreboard = sb;
                        break;
                    }
                }
                // If TempAccount is already in one of the scoreboards it checks the accountRoute if it is already in teh scoreboard account.
                if (alreadyInScoreboards) {
                    // Checking the maxRoutes of a user to see if they already have the current route there.
                    boolean routeAlreadyInScoreboards = false;
                    for (MaxRoute mr : tempScoreboard.maxRoutes) {
                        // MaxRoute is AccountRoute
                        if (mr.route == ar.getRoute()) {
                            routeAlreadyInScoreboards = true;
                            // If maxZone is lower than the AccountRoute ZoneReached it gets changed to that.
                            if (mr.maxZone < ar.getZoneReached()) {
                                mr.maxZone = ar.getZoneReached();
                            }
                            break;
                        }
                    }
                    if (!routeAlreadyInScoreboards) {
                        tempScoreboard.maxRoutes.add(new MaxRoute(ar.getRoute(), ar.getZoneReached()));
                    }
                }
                // Creates a new Scoreboard with the TempAccount.
                else {
                    Scoreboard newScoreBoard = new Scoreboard();
                    newScoreBoard.account = tempAccount;
                    List<MaxRoute> newMaxRoutes = new ArrayList<MaxRoute>();
                    newMaxRoutes.add(new MaxRoute(ar.getRoute(), ar.getZoneReached()));
                    newScoreBoard.setMaxRoutes(newMaxRoutes);
                    scoreboards.add(newScoreBoard);
                    tempScoreboard = newScoreBoard;
                }
            } else {
                if (tempAccount != tempScoreboard.account) {
                    for (Scoreboard sb : scoreboards) {
                        if (sb.account == tempAccount) {
                            tempScoreboard = sb;
                            break;
                        }
                    }
                }
                // Checking the maxRoutes of a user to see if they already have the current route there.
                boolean routeAlreadyInScoreboards = false;
                for (MaxRoute mr : tempScoreboard.maxRoutes) {
                    // MaxRoute is AccountRoute
                    if (mr.route == ar.getRoute()) {
                        routeAlreadyInScoreboards = true;
                        // If maxZone is lower than the AccountRoute ZoneReached it gets changed to that.
                        if (mr.maxZone < ar.getZoneReached()) {
                            mr.maxZone = ar.getZoneReached();
                        }
                        break;
                    }
                }
                if (!routeAlreadyInScoreboards) {
                    tempScoreboard.maxRoutes.add(new MaxRoute(ar.getRoute(), ar.getZoneReached()));
                }
            }
        }
        System.out.println(scoreboards);
        // Giving the points to the things.
        RouteZonePoint tempRouteZonePoint = new RouteZonePoint();
        List<RouteZonePoint> routeZonePoints = new ArrayList<RouteZonePoint>();
        // todo format scoreboard to routezonepoint -> working
        // Foreach Scoreboard check if they are in the route
        for (Scoreboard sb : scoreboards) {
            // Foreach MaxRoute add one to the route zone.
            for (MaxRoute mr : sb.maxRoutes) {
                boolean routeZonePointsAlreadyExists = false;
                // match the routezone point to the thing.
                for (RouteZonePoint rzp : routeZonePoints) {
                    if (rzp.route == mr.route) {
                        routeZonePointsAlreadyExists = true;
                        rzp.zonepoints[mr.maxZone - 1][1] += 1;
                    }
                }
                if (!routeZonePointsAlreadyExists) {
                    System.out.println("created new one");
                    // Create New Route Zone Point
                    RouteZonePoint newRouteZonePoint = new RouteZonePoint(mr.route);
                    // Add the maxRoute of an account to the route.
                    newRouteZonePoint.zonepoints[mr.maxZone - 1][1] += 1;
                    routeZonePoints.add(newRouteZonePoint);
                }
            }
        }
        System.out.println(routeZonePoints);
        // todo make loop that adds the higher zone count to the one below -> working
        for (RouteZonePoint addRzp : routeZonePoints) {
            for (int i = 2; i >= 0; i--) {
                addRzp.zonepoints[i][1] += addRzp.zonepoints[i + 1][1];
                System.out.println(addRzp.zonepoints[i][1]);
            }
        }
        // todo make for loop that changes count to actual points. -> Working.
        System.out.println(routeZonePoints.size());
        System.out.println(routeZonePoints);
        for (RouteZonePoint convertRzp : routeZonePoints) {
            for (int j = 0; j < 4; j++) {
                if (convertRzp.zonepoints[j][1] != 0) {
                    int zonePointsValue = 0;
                    switch (j) {
                        case 0:
                            zonePointsValue = 250;
                            break;
                        case 1:
                            zonePointsValue = 500;
                            break;
                        case 2:
                            zonePointsValue = 750;
                            break;
                        case 3:
                            zonePointsValue = 1000;
                            break;
                        default:
                            throw new NullPointerException();
                    }
                    convertRzp.zonepoints[j][1] = (int) (zonePointsValue / convertRzp.zonepoints[j][1]);
                }
            }
        }
        System.out.println(routeZonePoints);
        // todo add the points of the accounts. -> Working?
        // Temporary AccountCompetition to have it to add points to the account competition.
        AccountCompetition tempAccountCompetition = new AccountCompetition();
        // GetAccountCompetitions
        List<AccountCompetition> accountCompetitions = new ArrayList<AccountCompetition>();
        accountCompetitions = accountCompetitionRepository.findByCompetition_Id(routeCompetition.getCompetition().getId());
        // Looking through the RouteZonePoints per person.
        for (Scoreboard accSb : scoreboards) {
            // SetAccount To TempAccount
            tempAccount = accSb.getAccount();
            for (AccountCompetition accountCompetition : accountCompetitions) {
                if (accountCompetition.getAccount() == tempAccount) {
                    // SetAccountCompetition To TempAccountCompetition
                    tempAccountCompetition = accountCompetition;
                    for (MaxRoute accMr : accSb.maxRoutes) {
                        for (RouteZonePoint accRzp : routeZonePoints) {
                            if (accMr.getRoute() == accRzp.getRoute()) {
                                // Checks which zone was reached by the person.
                                int maxReachedZone = 0;
                                switch (accMr.maxZone) {
                                    case 1:
                                        maxReachedZone = 0;
                                        break;
                                    case 2:
                                        maxReachedZone = 1;
                                        break;
                                    case 3:
                                        maxReachedZone = 2;
                                        break;
                                    case 4:
                                        maxReachedZone = 3;
                                        break;
                                    default:
                                        throw new NullPointerException();
                                }
                                tempAccountCompetition.setScore(tempAccountCompetition.getScore() + accRzp.zonepoints[maxReachedZone][1]);
                            }
                        }
                    }
                    System.out.println(tempAccountCompetition);
                    accountCompetitionRepository.save(tempAccountCompetition);
                }
            }
        }
        // Foreach Rzp
        // Foreach Scoreboard.
        // SetPoints.
        // todo save to the database the point the accounts have.
        // Save Account
    }
}

