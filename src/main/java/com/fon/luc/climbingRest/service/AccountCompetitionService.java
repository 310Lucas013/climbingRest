package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.controller.AccountCompetitionController;
import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.AccountCompetition;
import com.fon.luc.climbingRest.repository.AccountCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class AccountCompetitionService {

    @Autowired
    AccountCompetitionRepository accountCompetitionRepository;

    public AccountCompetition createAccountCompetition(AccountCompetition accountCompetition) {
        return accountCompetitionRepository.save(accountCompetition);
    }

    public List<AccountCompetition> getAccountCompetitions(Long id) {
        return accountCompetitionRepository.findByCompetition_Id(id);
    }

    public int countParticipantsByCompetitionName(String name) {
        return accountCompetitionRepository.countAccountCompetitionByCompetitionNameContaining(name);
    }

    public List<AccountCompetition> getAccountCompetitionsByCompetitionName(String name) {
        return accountCompetitionRepository.findAccountCompetitionByCompetition_Name(name);
    }

}
