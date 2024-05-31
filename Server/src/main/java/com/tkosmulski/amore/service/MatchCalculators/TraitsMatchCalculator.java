package com.tkosmulski.amore.service.MatchCalculators;

import com.tkosmulski.amore.dto.FullPersonProfile;
import com.tkosmulski.amore.dto.WantedTrait;
import org.springframework.stereotype.Component;

@Component("AgeTraitsMatchCalculator")
public class TraitsMatchCalculator implements MatchCalculator {

    @Override
    public int calculate(FullPersonProfile profile, FullPersonProfile testedProfile) {
        int out = 0;
        for(int desiredTraitId : profile.desiredTraits().keySet()) {
            if(testedProfile.traits().contains(desiredTraitId)) {
                out+= profile.desiredTraits().get(desiredTraitId);
            }
        }
        return out;
    }
}
