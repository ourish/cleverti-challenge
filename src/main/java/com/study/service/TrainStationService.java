package com.study.service;

import com.study.domain.MatchingTrainStationResponse;

public interface TrainStationService {

  MatchingTrainStationResponse findMatchingTrainStations(String stationName);
}
