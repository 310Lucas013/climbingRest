package com.fon.luc.climbingRest.formData;

import lombok.Data;

@Data
public class AddAccountRouteCompetition {
    public String email;
    public Long routeId;
    public int zone;
    public Long competitionId;
}
