package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.LoginDataDto;
import com.tkosmulski.amore.dto.Person;
import com.tkosmulski.amore.persistence.PersonRepository;
import com.tkosmulski.amore.service.exceptions.BadCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonLoginService {
    PersonRepository personRepository;

    @Autowired
    public PersonLoginService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int login(LoginDataDto loginData){
        //returns id or throws exception for bad credentials
        Optional<Person> person = personRepository.findByEmail(loginData.getEmail());
        if(person.isEmpty() || !loginData.getPassword().equals(person.get().getPassword())){
            throw new BadCredentialsException();
        }
        return person.get().getId();
    }
}
