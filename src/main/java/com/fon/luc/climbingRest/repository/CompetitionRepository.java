package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    @Query("FROM Competition c " +
            "WHERE c.name = ?1")
    Competition findByName(String name);
}
