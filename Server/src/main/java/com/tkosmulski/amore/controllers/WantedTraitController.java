package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.*;
import com.tkosmulski.amore.service.WantedTraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wantedTrait")
public class WantedTraitController {
    WantedTraitService wantedTraitService;

    @Autowired
    public WantedTraitController(WantedTraitService wantedTraitService) {
        this.wantedTraitService = wantedTraitService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WantedTraitDto> getId(IdDto idDto){
        return wantedTraitService.getAllByPersonId(idDto.getId()).stream().map(WantedTraitDto::new).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(WantedTraitDto wantedTraitDto){
        wantedTraitService.add(new WantedTrait(new PersonTraitPrimaryKey(wantedTraitDto.getPersonId(), wantedTraitDto.getTraitId()),
                wantedTraitDto.getValue()));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(PersonTraitPrimaryKey personTraitPrimaryKey){
        wantedTraitService.delete(personTraitPrimaryKey);
    }
}
