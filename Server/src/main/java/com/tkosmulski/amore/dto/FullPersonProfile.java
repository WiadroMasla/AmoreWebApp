package com.tkosmulski.amore.dto;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public record FullPersonProfile(Person person, Set<Integer> traits, Map<Integer, Integer> desiredTraits) {
    //desired traits in form traitId -> Value (Weight)
}
