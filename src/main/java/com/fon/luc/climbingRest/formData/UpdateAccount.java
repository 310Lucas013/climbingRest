package com.fon.luc.climbingRest.formData;

import lombok.Data;

@Data
public class UpdateAccount {
    public String firstName;
    public String middleName;
    public String lastName;
    public String uid;
    public String email;
}
