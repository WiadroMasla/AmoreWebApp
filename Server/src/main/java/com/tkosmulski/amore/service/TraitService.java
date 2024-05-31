package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.Trait;
import com.tkosmulski.amore.persistence.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitService {
    TraitRepository traitRepository;

    @Autowired
    public TraitService(TraitRepository traitRepository) {
        this.traitRepository = traitRepository;
    }

    public List<Trait> getAll() {
        return traitRepository.findAll();
    }

}
