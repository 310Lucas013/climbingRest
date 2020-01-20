package com.fon.luc.climbingRest.messages;

public class AddAccountRouteMessage {
    private String email;
    private Long routeId;
    private int zone;

    public AddAccountRouteMessage(String email, Long routeId, int zone) {
        this.email = email;
        this.routeId = routeId;
        this.zone = zone;
    }

    public AddAccountRouteMessage() {
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

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }
}
