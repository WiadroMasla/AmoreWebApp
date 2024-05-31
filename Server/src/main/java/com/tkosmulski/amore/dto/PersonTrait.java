package com.tkosmulski.amore.dto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class PersonTrait {
    @EmbeddedId
    PersonTraitPrimaryKey id;

    public PersonTrait() {id = new PersonTraitPrimaryKey();}
    public PersonTrait(PersonTraitPrimaryKey personTraitPrimaryKey) {
        this.id = personTraitPrimaryKey;
    }

    public PersonTraitPrimaryKey getId() {
        return id;
    }

    public void setId(PersonTraitPrimaryKey personTraitPrimaryKey) {
        this.id = personTraitPrimaryKey;
    }
}
