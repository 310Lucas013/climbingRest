package com.fon.luc.climbingRest.formData;

import com.fon.luc.climbingRest.enums.Group;
import lombok.Data;

@Data
public class AddAccountCompetition {
    public String email;
    public String name;
    public Group group;

}
