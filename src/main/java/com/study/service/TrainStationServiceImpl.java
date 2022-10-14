package com.study.service;

import com.study.domain.MatchingTrainStationResponse;
import com.study.domain.TrainStation;
import com.study.repositories.TrainStationRepository;
import java.util.List;
import java.util.stream.Collectors;
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
  public MatchingTrainStationResponse findMatchingTrainStations(String stationName) {
    final TrainStation input = new TrainStation(stationName);
    final ExampleMatcher matcher = ExampleMatcher.matchingAny()
        .withMatcher("name", GenericPropertyMatchers.startsWith().ignoreCase());
    final List<TrainStation> matchingTrainStations = trainStationRepository.findAll(Example.of(input, matcher));

    return new MatchingTrainStationResponse(
        matchingTrainStations.stream()
            .map(trainStation -> trainStation.getName().split(stationName)[1].substring(0,1))
            .collect(Collectors.toSet()),
        matchingTrainStations.stream()
            .map(TrainStation::getName)
            .toList()
    );
  }
}
