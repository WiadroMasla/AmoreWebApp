package com.tkosmulski.amore.dto;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MatchPrimaryKey implements Serializable {
    private Integer personId;
    @Column(insertable = false)
    private String timeStamp;

    public MatchPrimaryKey(Integer personId, String timeStamp) {
        this.personId = personId;
        this.timeStamp = timeStamp;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
