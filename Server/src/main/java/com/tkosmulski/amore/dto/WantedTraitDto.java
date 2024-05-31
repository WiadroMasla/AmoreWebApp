package com.tkosmulski.amore.dto;

public class WantedTraitDto {
    private Integer personId;
    private Integer traitId;
    private Integer value;

    public WantedTraitDto() {
    }

    public WantedTraitDto(WantedTrait wantedTrait) {
        personId = wantedTrait.getId().getPersonId();
        traitId = wantedTrait.getId().getTraitId();
        value = wantedTrait.getValue();
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
