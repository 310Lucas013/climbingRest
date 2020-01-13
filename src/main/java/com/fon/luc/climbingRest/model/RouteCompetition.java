package com.fon.luc.climbingRest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "RouteCompetition",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"routeId", "competitionId"}))
public class RouteCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "routeId")
    Route route;

    @ManyToOne
    @JoinColumn(name = "competitionId")
    Competition competition;

    public RouteCompetition() {

    }

    public RouteCompetition(Route route, Competition competition) {
        this.route = route;
        this.competition = competition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
