package com.ticketmachine.bootstrap;

import com.ticketmachine.domain.TrainStation;
import com.ticketmachine.repositories.TrainStationRepository;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MockDataCommandLineRunner implements CommandLineRunner {

  private final TrainStationRepository trainStationRepository;

  public MockDataCommandLineRunner(TrainStationRepository trainStationRepository) {
    this.trainStationRepository = trainStationRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    log.info("Adding Mock Train Station Data...");
    try(Stream<String> fileStream = Files.lines(Paths.get("src/main/resources/trainStations.txt"))) {
      fileStream.forEach(this::addTrainStation);
    }
    log.info("Added " + trainStationRepository.count() + " mock Stations...");
  }

  private void addTrainStation(String stationName) {
    final TrainStation trainStation = new TrainStation(stationName.trim());
    trainStationRepository.save(trainStation);
  }
}
