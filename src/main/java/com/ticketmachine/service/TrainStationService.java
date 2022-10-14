package com.ticketmachine.service;

import com.ticketmachine.domain.MatchingTrainStationResponse;
import java.util.List;

public interface TrainStationService {

  MatchingTrainStationResponse findMatchingTrainStations(String stationName);

  List<String> getAllTrainStations();
}
