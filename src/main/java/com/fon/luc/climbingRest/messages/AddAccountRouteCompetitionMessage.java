package com.fon.luc.climbingRest.messages;

public class AddAccountRouteCompetitionMessage {
    private String email;
    private Long routeId;
    private int zone;
    private Long competitionId;

    public AddAccountRouteCompetitionMessage(String email, Long routeId, int zone, Long competitionId) {
        this.email = email;
        this.routeId = routeId;
        this.competitionId = competitionId;
        this.zone = zone;
    }

    public AddAccountRouteCompetitionMessage() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }
}
