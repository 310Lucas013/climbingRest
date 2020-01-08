package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.enums.Group;
import com.fon.luc.climbingRest.formData.AddAccountCompetition;
import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.model.AccountCompetition;
import com.fon.luc.climbingRest.model.Competition;
import com.fon.luc.climbingRest.service.AccountCompetitionService;
import com.fon.luc.climbingRest.service.AccountService;
import com.fon.luc.climbingRest.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/accountcompetitions")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountCompetitionController {

    @Autowired
    AccountCompetitionService accountCompetitionService;
    @Autowired
    AccountService accountService;
    @Autowired
    CompetitionService competitionService;

    @PostMapping
    public AccountCompetition createAccountCompetition(@Valid @RequestBody AddAccountCompetition addAccountCompetition) {
        System.out.println(addAccountCompetition.toString());
        AccountCompetition complete;
        Account account = accountService.findByEmail(addAccountCompetition.email);
        System.out.println(account);
        Competition competition = competitionService.findByName(addAccountCompetition.name);
        System.out.println(competition.toString());
        if (addAccountCompetition.group == null) {
            complete = new AccountCompetition(account, competition);
        }
        else {
            Group groupName = addAccountCompetition.group;
            complete = new AccountCompetition(account, competition, groupName);
        }
        return accountCompetitionService.createAccountCompetition(complete);
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public List<AccountCompetition> getAccountCompetitions(@PathVariable("id") Long id) {
        return accountCompetitionService.getAccountCompetitions(id);
    }

    @RequestMapping(value= "/participants/{name}", method = RequestMethod.GET)
    public int getParticipantsByCompetitionName(@PathVariable("name") String name) {
        return accountCompetitionService.countParticipantsByCompetitionName(name);
    }

    @RequestMapping(value= "/name/{name}", method = RequestMethod.GET)
    public List<AccountCompetition> getAccountCompetitionsByCompetitionName(@PathVariable("name") String name) {
        return accountCompetitionService.getAccountCompetitionsByCompetitionName(name);
    }

//    @GetMapping
//    public List<AccountCompetition> getAccountCompetitions() {
//        return accountCompetitionService.getAccountCompetitions();
//    }

}
