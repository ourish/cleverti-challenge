package com.ticketmachine.service;

import com.ticketmachine.domain.MatchingTrainStationResponse;

public interface TrainStationService {

  MatchingTrainStationResponse findMatchingTrainStations(String stationName);
}
