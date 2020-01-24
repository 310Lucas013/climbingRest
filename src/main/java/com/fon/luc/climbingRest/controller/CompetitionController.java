package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.model.Competition;
import com.fon.luc.climbingRest.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/competitions")
@CrossOrigin(origins = "http://localhost:4200")
public class CompetitionController {

    @Autowired
    CompetitionService competitionService;

    @PostMapping
    public Competition createCompetition(@Valid @RequestBody Competition competition) {
        Competition complete = new Competition(competition.getName(), competition.getStartDate(), competition.getEndDate());
        return competitionService.createCompetition(complete);
    }

    @GetMapping
    public Collection<Competition> getCompetitions() {
        return competitionService.getAllCompetitions();
    }

}
