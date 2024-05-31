package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.EmailDto;
import com.tkosmulski.amore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleController {
    PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmailDto> getAll(){
        return personService.getEmails();
    }
}
