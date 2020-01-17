package com.fon.luc.climbingRest.messages;

import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.AccountRoute;
import com.fon.luc.climbingRest.model.Route;

public class CreateAccountRouteMessage {
    private Account account;
    private Route route;
    private int maxZone;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getMaxZone() {
        return maxZone;
    }

    public void setMaxZone(int maxZone) {
        this.maxZone = maxZone;
    }

    public CreateAccountRouteMessage(Account account, Route route, int maxZone) {
        this.account = account;
        this.route = route;
        this.maxZone = maxZone;
    }
}
