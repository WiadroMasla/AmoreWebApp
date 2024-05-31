package com.tkosmulski.amore.persistence;

import com.tkosmulski.amore.dto.PersonTrait;
import com.tkosmulski.amore.dto.PersonTraitPrimaryKey;
import com.tkosmulski.amore.dto.WantedTrait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WantedTraitRepository extends JpaRepository<WantedTrait, PersonTraitPrimaryKey> {
    List<WantedTrait> findAllByIdPersonId(Integer personId);
}
