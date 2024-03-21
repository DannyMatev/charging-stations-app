package com.hubject.charging.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchWithinRadiusDTO {

  @Parameter(description = "Reference latitude", example = "41.295445")
  private Double longitude;

  @Parameter(description = "Reference latitude", example = "-80.817887")
  private Double latitude;

  @Parameter(description = "Radius", example = "1000000")
  private Double radius;
}
