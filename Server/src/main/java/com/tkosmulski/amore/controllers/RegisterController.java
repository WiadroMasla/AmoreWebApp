package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.Person;
import com.tkosmulski.amore.service.PersonRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    PersonRegisterService registerService;

    @Autowired
    public RegisterController(PersonRegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person add(Person person){
        person.nullify();
        return registerService.register(person);
    }

}
