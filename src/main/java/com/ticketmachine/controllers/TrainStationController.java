package com.ticketmachine.controllers;

import com.ticketmachine.domain.MatchingTrainStationResponse;
import com.ticketmachine.service.TrainStationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TrainStationController.BASE_URL)
public class TrainStationController {

  public static final String BASE_URL = "api/v1/trainticketmachine";

  private final TrainStationService trainStationService;

  public TrainStationController(TrainStationService trainStationService) {
    this.trainStationService = trainStationService;
  }

  @GetMapping("/{stationName}")
  MatchingTrainStationResponse getMatchingStationNames(@PathVariable String stationName) {
    return trainStationService.findMatchingTrainStations(stationName);
  }
}
