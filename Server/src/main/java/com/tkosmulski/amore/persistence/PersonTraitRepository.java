package com.tkosmulski.amore.persistence;

import com.tkosmulski.amore.dto.PersonTrait;
import com.tkosmulski.amore.dto.PersonTraitPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PersonTraitRepository extends JpaRepository<PersonTrait, PersonTraitPrimaryKey> {

    List<PersonTrait> findAllByIdPersonId(Integer id);
}
