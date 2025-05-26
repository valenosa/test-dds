package ar.utn.edu.frba.ddsi.models.dtos.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ExternalDisasterDTO {
  private Long id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private Double latitud;
  private Double longitud;
  @JsonProperty("fecha_hecho")
  private ZonedDateTime eventDate;
  @JsonProperty("updated_at")
  private ZonedDateTime updateDate;

}