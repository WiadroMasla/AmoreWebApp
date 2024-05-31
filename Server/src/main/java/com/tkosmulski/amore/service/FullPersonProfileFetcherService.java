package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.*;
import com.tkosmulski.amore.persistence.PersonTraitRepository;
import com.tkosmulski.amore.persistence.WantedTraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FullPersonProfileFetcherService {
    PersonTraitRepository personTraitRepository;
    WantedTraitRepository wantedTraitRepository;

    @Autowired
    public FullPersonProfileFetcherService(PersonTraitRepository personTraitRepository, WantedTraitRepository wantedTraitRepository) {
        this.personTraitRepository = personTraitRepository;
        this.wantedTraitRepository = wantedTraitRepository;
    }

    public FullPersonProfile fetch(Person person) {
        Collection<Integer> personTraitIds = personTraitRepository.findAllByIdPersonId(person.getId())
                . stream().map(PersonTrait::getId).map(PersonTraitPrimaryKey::getTraitId).collect(Collectors.toList());
        Map<Integer, Integer> traitValueMap = wantedTraitRepository.findAllByIdPersonId(person.getId())
                .stream().collect(Collectors.toMap(e -> e.getId().getTraitId(), e -> e.getValue()));


        return new FullPersonProfile(person,
                new HashSet<>(personTraitIds),
                traitValueMap);
    }
}
