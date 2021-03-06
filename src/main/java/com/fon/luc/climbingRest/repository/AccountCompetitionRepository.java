package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.AccountCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AccountCompetitionRepository extends JpaRepository<AccountCompetition, Long> {
    List<AccountCompetition> findByCompetition_Id(Long id);

    int countAccountCompetitionByCompetitionNameContaining(String name);

    List<AccountCompetition> findAccountCompetitionByCompetition_Name(String name);

    List<AccountCompetition> findAll();
}
