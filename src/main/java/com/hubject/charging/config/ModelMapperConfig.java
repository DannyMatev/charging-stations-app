package com.hubject.charging.config;

import com.hubject.charging.dto.CoordinatesDTO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    modelMapper.addConverter(pointToCoordinatesConverter(), Geometry.class, CoordinatesDTO.class);
    modelMapper.addConverter(coordinatesToPointConverter(), CoordinatesDTO.class, Geometry.class);

    return modelMapper;
  }

  private Converter<Geometry, CoordinatesDTO> pointToCoordinatesConverter() {
    return ctx -> {
      if (ctx.getSource() == null) {
        return null;
      } else {
        Point point = (Point) ctx.getSource();
        return new CoordinatesDTO(point.getX(), point.getY());
      }
    };
  }

  private Converter<CoordinatesDTO, Geometry> coordinatesToPointConverter() {
    return ctx ->
        ctx.getSource() == null
            ? null
            : new GeometryFactory()
                .createPoint(
                    new Coordinate(ctx.getSource().getLongitude(), ctx.getSource().getLatitude()));
  }
}
