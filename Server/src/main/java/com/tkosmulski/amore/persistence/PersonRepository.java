package com.tkosmulski.amore.persistence;

import com.tkosmulski.amore.dto.IdDto;
import com.tkosmulski.amore.dto.Person;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository <Person, Integer> {
    Optional<Person> findByEmail(String email);

    @Query(value = "SELECT dbo.Get_Match(?1) AS id", nativeQuery = true)
    Integer findMatchId(int id);

    @Query(value = "SELECT * FROM SINGLE_MEN", nativeQuery = true)
    Collection<Person> findAllSingleMen();

    @Query(value = "SELECT * FROM SINGLE_WOMEN", nativeQuery = true)
    Collection<Person> findAllSingleWomen();


}
