package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.FullPersonProfile;
import com.tkosmulski.amore.dto.Person;
import com.tkosmulski.amore.dto.PersonMatch;
import com.tkosmulski.amore.dto.PersonPair;
import com.tkosmulski.amore.persistence.MatchRepository;
import com.tkosmulski.amore.persistence.PersonRepository;
import com.tkosmulski.amore.service.MatchCalculators.MatchCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonMatchingService {
    PersonRepository personRepository;
    MatchRepository matchRepository;
    FullPersonProfileFetcherService profileFetcherService;

    @Autowired
    @Qualifier("AgeTraitsMatchCalculator")
    MatchCalculator matchCalculator;

    @Autowired
    public PersonMatchingService(PersonRepository personRepository,MatchRepository matchRepository, FullPersonProfileFetcherService profileFetcherService) {
        this.matchRepository = matchRepository;
        this.personRepository = personRepository;
        this.profileFetcherService = profileFetcherService;
    }

    private List<PersonPair> match(Collection<PersonMatch> men, Collection<PersonMatch> women) {
        for(PersonMatch man : men) {
            man.prepareQueue(women, matchCalculator);
        }

        Queue<PersonMatch> unpairedMenQueue = new LinkedList<>(men);

        while (unpairedMenQueue.peek() != null) {
            PersonMatch man = unpairedMenQueue.poll();
            PersonMatch woman = man.getPartnerQueue().poll();
            if(woman != null) {
                PersonMatch rejectedMan = woman.propose(man, matchCalculator);
                if(rejectedMan != null) {
                    unpairedMenQueue.add(rejectedMan);
                }
            }

        }

        return men.stream().filter(o -> o.getMatch() != null).map(o -> new PersonPair(o.getPersonProfile().person(),
                o.getMatch().getPersonProfile().person())).toList();
    }

    private Collection<PersonMatch> toPersonMatch(Collection<Person> people) {
        return people.stream().map(x -> new PersonMatch(profileFetcherService.fetch(x))).collect(Collectors.toList());
    }
    public List<PersonPair> start() {

       List<PersonPair> matchResult = match(toPersonMatch(personRepository.findAllSingleMen()),
                toPersonMatch(personRepository.findAllSingleWomen()));
       matchResult.stream().forEach(o -> matchRepository.matchPeople(o.person1().getId(), o.person2().getId()));

       return matchResult;
    }

}
