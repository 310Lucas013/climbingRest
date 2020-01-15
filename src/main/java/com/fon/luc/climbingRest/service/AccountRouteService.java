package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.formData.MaxRoute;
import com.fon.luc.climbingRest.formData.RouteZonePoint;
import com.fon.luc.climbingRest.formData.Scoreboard;
import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.AccountRoute;
import com.fon.luc.climbingRest.model.Route;
import com.fon.luc.climbingRest.model.RouteCompetition;
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
        // Going through all the AccountRoutes that belong to the competition.
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
        for (Scoreboard sb : scoreboards) {
            for (MaxRoute mr : sb.maxRoutes) {
                boolean routeZonePointsAlreadyExists = false;
                for (RouteZonePoint rzp : routeZonePoints) {
                    if (rzp.route == mr.route) {
                        routeZonePointsAlreadyExists = true;
                        rzp.zonepoints[mr.maxZone-1][1] += 1;
                    }
                }
                if (!routeZonePointsAlreadyExists) {
                    RouteZonePoint newRouteZonePoint = new RouteZonePoint(mr.route);
                    routeZonePoints.add(newRouteZonePoint);
                }
            }
        }
        System.out.println(routeZonePoints);
        // todo make for loop that changes count to actual points.
        // todo add the points of the accounts.
        // todo save to the database the point the accounts have.
    }
}

