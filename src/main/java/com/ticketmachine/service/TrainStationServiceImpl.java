package com.ticketmachine.service;

import com.ticketmachine.domain.MatchingTrainStationResponse;
import com.ticketmachine.domain.TrainStation;
import com.ticketmachine.repositories.TrainStationRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

@Service
public class TrainStationServiceImpl implements TrainStationService {

  private final TrainStationRepository trainStationRepository;

  public TrainStationServiceImpl(TrainStationRepository trainStationRepository) {
    this.trainStationRepository = trainStationRepository;
  }

  @Override
  public List<String> getAllTrainStations() {
    return trainStationRepository.findAll().stream()
        .map(TrainStation::getName)
        .toList();
  }

  @Override
  public MatchingTrainStationResponse findMatchingTrainStations(String stationName) {
    final TrainStation input = new TrainStation(stationName);
    final ExampleMatcher matcher = ExampleMatcher.matchingAny()
        .withMatcher("name", GenericPropertyMatchers.startsWith().ignoreCase());
    final List<TrainStation> matchingTrainStations = trainStationRepository.findAll(Example.of(input, matcher));

    return parseMatchingTrainStationResponse(matchingTrainStations, stationName);
  }

  private MatchingTrainStationResponse parseMatchingTrainStationResponse(List<TrainStation> matchingTrainStations,
      String input) {

    if(matchingTrainStations.size() == 1) {
      return new MatchingTrainStationResponse(null, matchingTrainStations.stream().map(TrainStation::getName).toList());
    }

    final HashSet<Character> nextPossibleCharacters = new HashSet<>();
    final ArrayList<String> trainStationNames = new ArrayList<>();

    for(TrainStation trainStation : matchingTrainStations) {
      final String trainStationName = trainStation.getName();
      trainStationNames.add(trainStationName);
      // if input is 'Ave', it will split Aveiro into Ave - iro
      final String lastPartOfStationName = trainStationName.split(input)[1];
      nextPossibleCharacters.add(lastPartOfStationName.charAt(0));
    }
    
    return new MatchingTrainStationResponse(nextPossibleCharacters, trainStationNames);
  }
}
