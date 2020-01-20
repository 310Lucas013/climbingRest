package com.fon.luc.climbingRest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

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
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "lastName")
    private String lastName;

//    @ManyToMany
//    @JoinTable(
//        name= "account_competition",
//        joinColumns = @JoinColumn(name = "accountId"),
//        inverseJoinColumns = @JoinColumn(name = "competitionId")
//    )
//    Set<Competition> competitions;


//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
//    Set<AccountCompetition> participations;

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

    public Account(Long id, String email, Date createdAt, String uid, String firstName, String middleName, String lastName) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.uid = uid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Account(String email, Date createdAt, String uid, String firstName, String middleName, String lastName) {
        this.email = email;
        this.createdAt = createdAt;
        this.uid = uid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }
}
