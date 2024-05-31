package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.EmailDto;
import com.tkosmulski.amore.dto.IdDto;
import com.tkosmulski.amore.dto.SafePerson;
import com.tkosmulski.amore.persistence.PersonRepository;
import com.tkosmulski.amore.service.exceptions.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Integer findMatchId(int id) {
        return personRepository.findMatchId(id);
    }

    public SafePerson findById(int id){
        return new SafePerson(personRepository.getReferenceById(id));
    }

    public Integer findIdByEmail(String email) {
        return personRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email)).getId();
    }

    public List<EmailDto> getEmails() {
        return personRepository.findAll().stream().map(x -> new EmailDto(x.getEmail())).toList();
    }
}
