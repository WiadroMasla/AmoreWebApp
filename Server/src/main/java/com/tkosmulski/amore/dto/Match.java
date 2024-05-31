package com.tkosmulski.amore.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;

@Entity
public class Match {
    @EmbeddedId
    private MatchPrimaryKey matchPrimaryKey;
    private Integer matchId;

    public Match(MatchPrimaryKey primaryKey, Integer matchId) {
        this.matchPrimaryKey = primaryKey;
        this.matchId = matchId;
    }

    public MatchPrimaryKey getMatchPrimaryKey() {
        return matchPrimaryKey;
    }

    public void setMatchPrimaryKey(MatchPrimaryKey matchPrimaryKey) {
        this.matchPrimaryKey = matchPrimaryKey;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }
}
