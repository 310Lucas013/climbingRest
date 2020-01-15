package com.fon.luc.climbingRest.formData;

import com.fon.luc.climbingRest.model.Route;
import lombok.Data;

@Data
public class MaxRoute {
    public Route route;
    public int maxZone;

    public MaxRoute() {
    }

    public MaxRoute(Route route, int maxZone) {
        this.route = route;
        this.maxZone = maxZone;
    }

    public Route getRoute() {
        return route;
    }

    public int getMaxZone() {
        return maxZone;
    }
}
