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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "routeId")
    Route route;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "competitionId")
    Competition competition;

    public RouteCompetition() {

    }

    public RouteCompetition(Route route, Competition competition) {
        this.route = route;
        this.competition = competition;
    }
}
