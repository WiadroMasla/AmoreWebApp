package com.tkosmulski.amore.persistence;

import com.tkosmulski.amore.dto.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitRepository extends JpaRepository<Trait, Integer> {
}
