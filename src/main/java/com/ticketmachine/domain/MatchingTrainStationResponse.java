package com.ticketmachine.domain;

import java.util.List;
import java.util.Set;

public record MatchingTrainStationResponse(Set<Character> nextPossibleCharacters,
                                           List<String> matchingStations) {

}
