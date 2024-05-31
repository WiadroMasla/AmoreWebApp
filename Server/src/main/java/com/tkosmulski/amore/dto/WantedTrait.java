package com.tkosmulski.amore.dto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class WantedTrait {
    @EmbeddedId
    private PersonTraitPrimaryKey id;
    private Integer value;

    public WantedTrait(PersonTraitPrimaryKey id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public WantedTrait() {
        id = new PersonTraitPrimaryKey();
    }

    public PersonTraitPrimaryKey getId() {
        return id;
    }

    public void setId(PersonTraitPrimaryKey id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
