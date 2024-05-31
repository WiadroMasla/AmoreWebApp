package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.Person;
import com.tkosmulski.amore.persistence.PersonRepository;
import com.tkosmulski.amore.service.exceptions.EmailTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonRegisterService {
    PersonRepository personRepository;

    @Autowired
    public PersonRegisterService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person register(Person person) {
        Optional<Person> existingRecord = personRepository.findByEmail(person.getEmail());
        if(existingRecord.isEmpty()) {
            return personRepository.save(person);
        }
        throw new EmailTakenException(person.getEmail());
    }


}
