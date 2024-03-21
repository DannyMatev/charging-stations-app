package com.hubject.charging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStationUpdateDTO {
  private CoordinatesDTO location;
  private String zipcode;
}
