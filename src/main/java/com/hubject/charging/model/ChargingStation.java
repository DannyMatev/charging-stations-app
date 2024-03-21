package com.hubject.charging.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "charging_stations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChargingStation {

  @Id @GeneratedValue private Long id;

  @Column(name = "uuid", unique = true)
  private String uuid;

  @Column(name = "location", columnDefinition = "geography(Point,4326)")
  private Geometry location;

  @Column(name = "zipcode")
  private String zipcode;
}
