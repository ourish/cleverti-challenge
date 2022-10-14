package com.ticketmachine.repositories;

import com.ticketmachine.domain.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainStationRepository extends JpaRepository<TrainStation, String> {
}
