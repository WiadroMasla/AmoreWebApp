package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.SafePerson;
import com.tkosmulski.amore.persistence.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    PersonService personService;
    MatchRepository matchRepository;

    @Autowired
    public MatchService(PersonService personService, MatchRepository matchRepository) {
        this.personService = personService;
        this.matchRepository = matchRepository;
    }


    public SafePerson getMatch(int personId) {
        Integer matchId = personService.findMatchId(personId);
        if(matchId == null) {
            return null;
        }
        return personService.findById(matchId);
    }

    public void removeMatch(Integer id) {
        matchRepository.splitPeople(id);
    }
}
