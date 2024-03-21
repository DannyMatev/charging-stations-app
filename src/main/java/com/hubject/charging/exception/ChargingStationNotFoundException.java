package com.hubject.charging.exception;

public class ChargingStationNotFoundException extends RuntimeException {
  public ChargingStationNotFoundException(String uuid) {
    super(String.format("Charging station with uuid %s does not exist", uuid));
  }
}
