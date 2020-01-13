package com.fon.luc.climbingRest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "AccountRoute",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"accountId", "routeId"}))
public class AccountRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "accountId")
    Account account;

    @JsonBackReference
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

    public AccountRoute(Account account, Route route, int zoneReached, Date attemptDate) {
        this.account = account;
        this.route = route;
        this.zoneReached = zoneReached;
        this.attemptDate = attemptDate;
    }

    public int getZoneReached() {
        return zoneReached;
    }

    public Date getAttemptDate() {
        return attemptDate;
    }
}
