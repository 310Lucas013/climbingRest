package com.fon.luc.climbingRest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    protected long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt;
    @Column(name = "uid")
    private String uid;

    public Account() {
    }

    public Account(long id) {
        this.id = id;
    }

    public Account(String email, Date createdAt) {
        this.email = email;
        this.createdAt = createdAt;
    }

    public Account(String email, String uid) {
        this.email = email;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        this.createdAt = calendar.getTime();
        this.uid = uid;
    }

    public Account(String email) {
        this.email = email;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        this.createdAt = calendar.getTime();
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
