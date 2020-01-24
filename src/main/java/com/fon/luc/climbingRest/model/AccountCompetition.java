package com.fon.luc.climbingRest.model;

import com.fon.luc.climbingRest.enums.Group;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AccountCompetition",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"accountId", "competitionId"}))
public class AccountCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    Account account;

    @ManyToOne
    @JoinColumn(name = "competitionId")
    Competition competition;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupName")
    Group groupName;

    @Column(name = "score")
    int score;

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

    public AccountCompetition(Account account, Competition competition, Group group, int score) {
        this.account = account;
        this.competition = competition;
        this.groupName = group;
        this.score = score;
    }

    public Group getGroupName() {
        return groupName;
    }

    public Account getAccount() {
        return account;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
