package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.EmailDto;
import com.tkosmulski.amore.dto.IdDto;
import com.tkosmulski.amore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person/email")
public class PersonByEmailController {

    PersonService personService;

    @Autowired
    public PersonByEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public IdDto getId(EmailDto emailDto){
        return new IdDto(personService.findIdByEmail(emailDto.getEmail()));
    }

}
