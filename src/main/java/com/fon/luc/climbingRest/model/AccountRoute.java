package com.fon.luc.climbingRest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "AccountRoute")
public class AccountRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    Account account;

    @ManyToOne
    @JoinColumn(name = "routeId")
    Route route;

    @Column(name = "zoneReached")
    private int zoneReached;

    @Column(name = "attemptDate")
    private Date attemptDate;

    public AccountRoute() {
    }

    public AccountRoute(Account account, Route route) {
        this.account = account;
        this.route = route;
    }

    public AccountRoute(Account account, Route route, int zoneReached) {
        this.account = account;
        this.route = route;
        this.zoneReached = zoneReached;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        this.attemptDate = calendar.getTime();
    }

    public int getZoneReached() {
        return zoneReached;
    }

    public Date getAttemptDate() {
        return attemptDate;
    }

    public Long getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public Account getAccount() {
        return account;
    }
}
