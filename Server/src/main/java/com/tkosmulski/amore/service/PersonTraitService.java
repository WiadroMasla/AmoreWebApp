package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.PersonTrait;
import com.tkosmulski.amore.persistence.PersonTraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonTraitService {
    PersonTraitRepository personTraitRepository;

    @Autowired
    public PersonTraitService(PersonTraitRepository personTraitRepository) {
        this.personTraitRepository = personTraitRepository;
    }

    public List<PersonTrait> getAllByPersonId(int personId) {
        return personTraitRepository.findAllByIdPersonId(personId);
    }

    public void add(PersonTrait personTrait) {
        personTraitRepository.saveAndFlush(personTrait);
    }

    public void delete(PersonTrait personTrait) {
        personTraitRepository.delete(personTrait);
    }
}
