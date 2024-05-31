package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.Person;
import com.tkosmulski.amore.dto.PersonPair;
import com.tkosmulski.amore.service.PersonMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/matcher")
public class MatcherController {
    PersonMatchingService personMatchingService;

    @Autowired
    public MatcherController(PersonMatchingService personMatchingService) {
        this.personMatchingService = personMatchingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<PersonPair> add(){
        return personMatchingService.start();
    }
}
