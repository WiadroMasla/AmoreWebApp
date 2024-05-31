package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.IdDto;
import com.tkosmulski.amore.dto.SafePerson;
import com.tkosmulski.amore.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/match", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchController {
    MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<SafePerson> add(IdDto idDto){
        SafePerson safePerson = matchService.getMatch(idDto.getId());
        return ResponseEntity.ok(safePerson);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void remove(IdDto idDto) {
        matchService.removeMatch(idDto.getId());
    }
}
