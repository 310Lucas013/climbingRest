package com.fon.luc.climbingRest.formData;

import lombok.Data;

@Data
public class AddAccountRoute {
    public String email;
    public Long routeId;
    public int zone;
}
