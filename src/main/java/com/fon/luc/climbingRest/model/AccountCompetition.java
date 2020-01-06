package com.fon.luc.climbingRest.model;

import com.fon.luc.climbingRest.embeddable.AccountCompetitionKey;
import com.fon.luc.climbingRest.enums.Group;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AccountCompetition")
public class AccountCompetition {

    @EmbeddedId
    AccountCompetitionKey id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    Account account;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competitionId")
    Competition competition;

    @Column(name = "group")
    @Enumerated(EnumType.STRING)
    private Group group;

    public Group getGroup() {
        return group;
    }
}
