package com.fon.luc.climbingRest.model;

import com.fon.luc.climbingRest.enums.ColorEnum;
import com.fon.luc.climbingRest.enums.DifficultyBase;
import com.fon.luc.climbingRest.enums.PlusMinus;
import com.fon.luc.climbingRest.enums.SecuringMethod;
import lombok.Data;

import javax.persistence.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private DifficultyBase difficulty;
    @Enumerated(EnumType.STRING)
    @Column(name = "difficultyPlusMinus")
    private PlusMinus plusMinus;
    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private ColorEnum color;
    @Enumerated(EnumType.STRING)
    @Column(name = "securingMethod")
    private SecuringMethod securingMethod;
    @Column(name = "buildDate")
    private Date buildDate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DifficultyBase getDifficulty() {
        return difficulty;
    }

    public PlusMinus getPlusMinus() {
        return plusMinus;
    }

    public ColorEnum getColor() {
        return color;
    }

    public SecuringMethod getSecuringMethod() {
        return securingMethod;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public Route() {

    }

    public Route(String name) {
        this.name = name;
        this.difficulty = DifficultyBase.SIXB;
        this.plusMinus = PlusMinus.PLUS;
        this.color = ColorEnum.WHITE;
        this.securingMethod = SecuringMethod.LEAD;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        this.buildDate = calendar.getTime();
    }

    public Route(String name, DifficultyBase difficultyBase, PlusMinus plusMinus, ColorEnum colorEnum, SecuringMethod securingMethod) {
        this.name = name;
        this.difficulty = difficultyBase;
        this.plusMinus = plusMinus;
        this.color = colorEnum;
        this.securingMethod = securingMethod;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        this.buildDate = calendar.getTime();
    }
}
