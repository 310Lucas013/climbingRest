package com.fon.luc.climbingRest.formData;

import com.fon.luc.climbingRest.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class Scoreboard {
    public Account account;
    public List<MaxRoute> maxRoutes;

    public void setMaxRoutes(List<MaxRoute> maxRoutes) {
        this.maxRoutes = maxRoutes;
    }

    public Account getAccount() {
        return account;
    }

    public List<MaxRoute> getMaxRoutes() {
        return maxRoutes;
    }
}
