package com.tkosmulski.amore.persistence;

import com.tkosmulski.amore.dto.Match;
import com.tkosmulski.amore.dto.MatchPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, MatchPrimaryKey> {
    @Procedure("Match_People")
    void matchPeople(Integer id1, Integer id2);

    @Procedure("Split_People")
    void splitPeople(Integer id);
}
