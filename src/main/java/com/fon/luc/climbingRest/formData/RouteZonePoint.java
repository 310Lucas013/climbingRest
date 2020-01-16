package com.fon.luc.climbingRest.formData;

import com.fon.luc.climbingRest.model.Route;
import lombok.Data;

@Data
public class RouteZonePoint {
    public Route route;
    // First value is the zone, second is the count of people/ points.
    public int[][] zonepoints;

    public RouteZonePoint(Route route, int[][] zone) {
        this.route = route;
        this.zonepoints = zone;
    }

    public RouteZonePoint(Route route) {
        this.route = route;
        this.zonepoints = new int[4][4];
        this.zonepoints[0][0] = 1;
        this.zonepoints[0][1] = 0;
        this.zonepoints[1][0] = 2;
        this.zonepoints[1][1] = 0;
        this.zonepoints[2][0] = 3;
        this.zonepoints[2][1] = 0;
        this.zonepoints[3][0] = 4;
        this.zonepoints[3][1] = 0;
    }

    public RouteZonePoint() {
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int[][] getZonepoints() {
        return zonepoints;
    }

    public void setZonepoints(int[][] zonepoints) {
        this.zonepoints = zonepoints;
    }
}

