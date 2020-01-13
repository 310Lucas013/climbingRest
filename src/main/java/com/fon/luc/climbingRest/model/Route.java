package com.fon.luc.climbingRest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeId")
    protected long id;
    @Column(name = "name")
    private String name;


}
