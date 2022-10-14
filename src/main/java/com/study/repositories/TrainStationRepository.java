package com.study.repositories;

import com.study.domain.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainStationRepository extends JpaRepository<TrainStation, String> {
}
