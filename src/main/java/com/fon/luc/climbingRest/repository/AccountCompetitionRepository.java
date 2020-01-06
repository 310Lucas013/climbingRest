package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.embeddable.AccountCompetitionKey;
import com.fon.luc.climbingRest.model.AccountCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AccountCompetitionRepository extends JpaRepository<AccountCompetition, AccountCompetitionKey> {

}
