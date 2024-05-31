package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.PersonTraitPrimaryKey;
import com.tkosmulski.amore.dto.Trait;
import com.tkosmulski.amore.service.TraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trait")
public class TraitController {
    TraitService traitService;

    @Autowired
    public TraitController(TraitService traitService) {
        this.traitService = traitService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getAll(){
        return traitService.getAll();
    }

}
