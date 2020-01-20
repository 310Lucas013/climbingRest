package com.fon.luc.climbingRest.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

public class AccountModelTests {

    @Test
    public void constructorAccountTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        String email = "LLHVerman@outlook.com";
        Date createdAt = calendar.getTime();
        String uid = "ajskldfjqowe123lkajsd";
        String firstName = "Luuk";
        String middleName = "Van";
        String lastName = "Meer";

        Account account = new Account(email, createdAt, uid, firstName, middleName, lastName);

        assertNotNull(account.getId());
        assertEquals(email, account.getEmail());
        assertEquals(uid, account.getUid());
        assertEquals(firstName, account.getFirstName());
        assertEquals(middleName, account.getMiddleName());
        assertEquals(lastName, account.getLastName());
        assertNotNull(account.getCreatedAt());

    }

    @Test
    public void setterAccountTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        String email = "LLHVerman@outlook.com";
        Date createdAt = calendar.getTime();
        String uid = "ajskldfjqowe123lkajsd";
        String firstName = "Luuk";
        String middleName = "Van";
        String lastName = "Meer";

        Account account = new Account();

        account.setEmail(email);
        account.setCreatedAt(createdAt);
        account.setUid(uid);
        account.setFirstName(firstName);
        account.setMiddleName(middleName);
        account.setLastName(lastName);

        assertEquals(email, account.getEmail());
        assertEquals(uid, account.getUid());
        assertEquals(firstName, account.getFirstName());
        assertEquals(middleName, account.getMiddleName());
        assertEquals(lastName, account.getLastName());
        assertEquals(createdAt, account.getCreatedAt());
    }
}
