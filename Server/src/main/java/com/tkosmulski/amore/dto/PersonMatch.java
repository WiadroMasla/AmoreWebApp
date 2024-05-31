package com.tkosmulski.amore.dto;

import com.tkosmulski.amore.service.MatchCalculators.MatchCalculator;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class PersonMatch {
    private FullPersonProfile personProfile;
    private PersonMatch match;
    private Integer matchValue;
    private Queue<PersonMatch> partnerQueue;

    public PersonMatch(FullPersonProfile personProfile) {
        this.personProfile = personProfile;
        match = null;
        matchValue = null;
        partnerQueue = new LinkedList<>();
    }

    public static void match(PersonMatch person1, PersonMatch person2) {
        person1.resetMatch();
        person2.resetMatch();
        person1.match = person2;
        person2.match = person1;
    }

    public FullPersonProfile getPersonProfile() {
        return personProfile;
    }

    public PersonMatch getMatch() {
        return match;
    }

    public void resetMatch() {
        if(match != null) {
            match.match = null;
            match = null;
        }
    }

    public Queue<PersonMatch> getPartnerQueue() {
        return partnerQueue;
    }

    public void prepareQueue(Collection<PersonMatch> potentialPartners, MatchCalculator matchCalculator) {
        partnerQueue = new LinkedList<>(potentialPartners.stream()
                .sorted(new PersonMatchComparator(matchCalculator, personProfile).reversed()).toList());
    }

    public PersonMatch propose(PersonMatch proposingPerson, MatchCalculator matchCalculator) {
        //returns rejected PersonMatch, can return null
        PersonMatch out = proposingPerson;
        int proposingPersonMatchValue = matchCalculator.calculate(this.personProfile, proposingPerson.personProfile);
        if(match == null || proposingPersonMatchValue > matchValue) {
            //replace
            out = this.match;
            match(this, proposingPerson);
            matchValue = proposingPersonMatchValue;
        }
        return out;
    }

    private static class PersonMatchComparator implements Comparator<PersonMatch> {
        private MatchCalculator matchCalculator;
        private  FullPersonProfile basePerson;

        public PersonMatchComparator(MatchCalculator matchCalculator, FullPersonProfile basePerson) {
            this.matchCalculator = matchCalculator;
            this.basePerson = basePerson;
        }

        @Override
        public int compare(PersonMatch o1, PersonMatch o2) {
            return Integer.valueOf(matchCalculator.calculate(basePerson, o1.personProfile))
                    .compareTo(matchCalculator.calculate(basePerson, o2.personProfile));
        }
    }
}
