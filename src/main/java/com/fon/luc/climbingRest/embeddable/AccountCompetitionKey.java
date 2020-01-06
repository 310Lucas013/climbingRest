package com.fon.luc.climbingRest.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AccountCompetitionKey implements Serializable {

    @Column(name = "accountId")
    Long accountId;

    @Column(name = "competitionId")
    Long competitionId;
}
