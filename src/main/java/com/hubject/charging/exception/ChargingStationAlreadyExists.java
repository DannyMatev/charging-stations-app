package com.hubject.charging.exception;

public class ChargingStationAlreadyExists extends RuntimeException {
  public ChargingStationAlreadyExists(String uuid) {
    super(String.format("Charging station with uuid %s already exists", uuid));
  }
}
