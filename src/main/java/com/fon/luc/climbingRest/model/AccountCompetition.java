package com.fon.luc.climbingRest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fon.luc.climbingRest.embeddable.AccountCompetitionKey;
import com.fon.luc.climbingRest.enums.Group;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "AccountCompetition",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"accountId", "competitionId"}))
public class AccountCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @EmbeddedId
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
//    AccountCompetitionKey id;
    @JsonBackReference
    @ManyToOne
//    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    Account account;

    @JsonBackReference
    @ManyToOne
//    @MapsId("competitionId")
    @JoinColumn(name = "competitionId")
    Competition competition;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupName")
    Group groupName;

    public AccountCompetition() {

    }

    public AccountCompetition(Account account, Competition competition) {
        this.account = account;
        this.competition = competition;
    }

    public AccountCompetition(Account account, Competition competition, Group group) {
        this.account = account;
        this.competition = competition;
        this.groupName = group;
    }

    public Group getGroupName() {
        return groupName;
    }

    public Account getAccount() {
        return account;
    }

    //    public AccountCompetitionKey getId() { return id;}
}
