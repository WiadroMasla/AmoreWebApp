package com.tkosmulski.amore.dto;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PersonTraitPrimaryKey implements Serializable {
    private Integer personId;
    private Integer traitId;

    public PersonTraitPrimaryKey() {
    }

    public PersonTraitPrimaryKey(Integer personId, Integer traitId) {
        this.personId = personId;
        this.traitId = traitId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getTraitId() {
        return traitId;
    }

    public void setTraitId(Integer traitId) {
        this.traitId = traitId;
    }
}
