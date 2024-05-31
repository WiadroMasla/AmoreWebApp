package com.tkosmulski.amore.service;

import com.tkosmulski.amore.dto.PersonTrait;
import com.tkosmulski.amore.dto.PersonTraitPrimaryKey;
import com.tkosmulski.amore.dto.WantedTrait;
import com.tkosmulski.amore.persistence.WantedTraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WantedTraitService {
    WantedTraitRepository wantedTraitRepository;

    @Autowired
    public WantedTraitService(WantedTraitRepository wantedTraitRepository) {
        this.wantedTraitRepository = wantedTraitRepository;
    }

    public List<WantedTrait> getAllByPersonId(int personId) {
        return wantedTraitRepository.findAllByIdPersonId(personId);
    }

    public void add(WantedTrait wantedTrait) {
        wantedTraitRepository.saveAndFlush(wantedTrait);
    }

    public void delete(PersonTraitPrimaryKey primaryKey) {
        wantedTraitRepository.deleteById(primaryKey);
    }
}
