package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.model.Competition;
import com.fon.luc.climbingRest.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class CompetitionService {

    @Autowired
    CompetitionRepository competitionRepository;

    public Competition createCompetition(Competition competition) {return competitionRepository.save(competition);}

    public Competition findByName(String name) {return competitionRepository.findByName(name);}
}
