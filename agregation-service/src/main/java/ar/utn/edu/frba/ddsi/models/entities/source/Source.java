package ar.utn.edu.frba.ddsi.models.entities.source;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Source {
  @Setter
  private Long id;
  private String baseUrl;
}