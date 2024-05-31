package com.tkosmulski.amore.service.MatchCalculators;

import com.tkosmulski.amore.dto.FullPersonProfile;

public interface MatchCalculator {
    //Calculates how well person 2 matches person1 preferences
    int calculate(FullPersonProfile profile, FullPersonProfile testedProfile);
}
