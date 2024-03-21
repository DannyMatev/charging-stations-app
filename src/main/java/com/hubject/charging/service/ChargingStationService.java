package com.hubject.charging.service;

import com.hubject.charging.dto.ChargingStationDTO;
import com.hubject.charging.dto.ChargingStationUpdateDTO;
import com.hubject.charging.dto.SearchWithinRadiusDTO;
import com.hubject.charging.exception.ChargingStationAlreadyExists;
import com.hubject.charging.exception.ChargingStationNotFoundException;
import com.hubject.charging.model.ChargingStation;
import com.hubject.charging.repository.ChargingStationRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargingStationService {

  private final ChargingStationRepository repository;
  private final ModelMapper modelMapper;

  public Optional<ChargingStationDTO> fetchByUuid(String id) {
    return repository.findOneByUuid(id).map(o -> modelMapper.map(o, ChargingStationDTO.class));
  }

  public List<ChargingStationDTO> fetchChargingStations(SearchWithinRadiusDTO searchDTO) {
    if (searchDTO.getLatitude() != null
        && searchDTO.getLongitude() != null
        && searchDTO.getRadius() != null) {
      return fetchAllWithinRadius(
          searchDTO.getLatitude(), searchDTO.getLongitude(), searchDTO.getRadius());
    }

    return repository.findAll().stream()
        .map(o -> modelMapper.map(o, ChargingStationDTO.class))
        .collect(Collectors.toList());
  }

  private List<ChargingStationDTO> fetchAllWithinRadius(
      double longitude, double latitude, double radius) {

    var referenceLocation = new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
    referenceLocation.setSRID(4326);

    return repository
        .findWithinRadius(referenceLocation.getX(), referenceLocation.getY(), radius)
        .stream()
        .map(o -> modelMapper.map(o, ChargingStationDTO.class))
        .collect(Collectors.toList());
  }

  public void create(ChargingStationDTO chargingStationDTO) {
    var chargingStationExists = repository.findOneByUuid(chargingStationDTO.getUuid()).isPresent();

    if (chargingStationExists) {
      throw new ChargingStationAlreadyExists(chargingStationDTO.getUuid());
    }

    var chargingStation = modelMapper.map(chargingStationDTO, ChargingStation.class);
    repository.save(chargingStation);
  }

  public ChargingStationDTO update(ChargingStationUpdateDTO updateDTO, String uuid) {
    var existing = repository.findOneByUuid(uuid);

    if (existing.isEmpty()) {
      throw new ChargingStationNotFoundException(uuid);
    }

    var updatedChargingStation = modelMapper.map(updateDTO, ChargingStation.class);

    updatedChargingStation.setUuid(uuid);
    updatedChargingStation.setId(existing.get().getId());

    repository.save(updatedChargingStation);

    return modelMapper.map(updatedChargingStation, ChargingStationDTO.class);
  }

  public void deleteByUuid(String uuid) {
    var existing = repository.findOneByUuid(uuid);
    existing.ifPresent(repository::delete);
  }
}
