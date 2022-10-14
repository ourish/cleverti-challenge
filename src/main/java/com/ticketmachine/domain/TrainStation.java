package com.ticketmachine.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrainStation {

  @Id
  private String name;

  public TrainStation() {
  }

  public TrainStation(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
