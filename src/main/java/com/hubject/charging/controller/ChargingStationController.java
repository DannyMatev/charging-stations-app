package com.hubject.charging.controller;

import com.hubject.charging.dto.ChargingStationDTO;
import com.hubject.charging.dto.ChargingStationUpdateDTO;
import com.hubject.charging.dto.SearchWithinRadiusDTO;
import com.hubject.charging.service.ChargingStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController("/charging-stations")
@RequiredArgsConstructor
public class ChargingStationController {

  private final ChargingStationService chargingStationService;

  @Operation(
      summary = "Get a charging station by uuid",
      description = "Returns a charging station for the given uuid")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(
            responseCode = "404",
            description = "Not found - The charging station was not found")
      })
  @GetMapping("/{uuid}")
  public ResponseEntity<ChargingStationDTO> getChargingStationById(@PathVariable String uuid) {
    return ResponseEntity.of(chargingStationService.fetchByUuid(uuid));
  }

  @Operation(
      summary = "Get all or a subset of charging stations in a radius",
      description =
          "Returns all charging stations or a subset of charging stations in a radius for a reference coordinates if parameters are provided.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
      })
  @GetMapping
  public ResponseEntity<List<ChargingStationDTO>> getChargingStations(
      @ParameterObject SearchWithinRadiusDTO searchDTO) {
    return ResponseEntity.ok(chargingStationService.fetchChargingStations(searchDTO));
  }

  @Operation(summary = "Create a charging station", description = "Creates a charging station")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(
            responseCode = "400",
            description = "Charging station with the given uuid already exists"),
      })
  @PostMapping
  public ResponseEntity<ChargingStationDTO> createChargingStation(
      @RequestBody ChargingStationDTO chargingStationDTO) {
    chargingStationService.create(chargingStationDTO);

    var resourceUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path(chargingStationDTO.getUuid())
            .build()
            .toUri();

    return ResponseEntity.created(resourceUri).body(chargingStationDTO);
  }

  @Operation(summary = "Update a charging station", description = "Update a charging station")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(
            responseCode = "404",
            description = "Charging station with the given uuid does not exist"),
      })
  @PutMapping("/{uuid}")
  public ResponseEntity<ChargingStationDTO> updateChargingStation(
      @RequestBody ChargingStationUpdateDTO updateDTO, @PathVariable String uuid) {
    var updatedChargingStation = chargingStationService.update(updateDTO, uuid);

    return ResponseEntity.ok(updatedChargingStation);
  }

  @Operation(
      summary = "Deletes a charging station",
      description = "Delete a charging station for a given uuid")
  @ApiResponses(
      value = {@ApiResponse(responseCode = "204", description = "Successfully retrieved")})
  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> deleteByUuid(@PathVariable String uuid) {
    chargingStationService.deleteByUuid(uuid);

    return ResponseEntity.noContent().build();
  }
}
