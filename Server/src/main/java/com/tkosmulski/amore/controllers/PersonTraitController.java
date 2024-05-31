package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.IdDto;
import com.tkosmulski.amore.dto.PersonTrait;
import com.tkosmulski.amore.dto.PersonTraitPrimaryKey;
import com.tkosmulski.amore.service.PersonTraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personTrait")
public class PersonTraitController {
    PersonTraitService personTraitService;
    @Autowired
    public PersonTraitController(PersonTraitService personTraitService) {
        this.personTraitService = personTraitService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonTrait> getId(IdDto idDto){
        return personTraitService.getAllByPersonId(idDto.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(PersonTraitPrimaryKey personTraitPrimaryKey){
        personTraitService.add(new PersonTrait(personTraitPrimaryKey));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(PersonTraitPrimaryKey personTraitPrimaryKey){
        personTraitService.delete(new PersonTrait(personTraitPrimaryKey));
    }
}
