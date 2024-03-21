package com.hubject.charging.repository;

import com.hubject.charging.model.ChargingStation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

  Optional<ChargingStation> findOneByUuid(String uuid);

  @Query(
      value =
          "SELECT * FROM charging_stations  WHERE ST_DWithin(location, CAST(ST_SetSRID(ST_Point(:longitude, :latitude), 4326) AS geography), :radius)",
      nativeQuery = true)
  List<ChargingStation> findWithinRadius(
      @Param("latitude") double latitude,
      @Param("longitude") double longitude,
      @Param("radius") double radius);
}
