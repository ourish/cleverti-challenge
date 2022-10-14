package com.study.domain;

import java.util.List;
import java.util.Set;

public record MatchingTrainStationResponse(Set<String> nextPossibleCharacters,
                                           List<String> matchingStations) {

}
