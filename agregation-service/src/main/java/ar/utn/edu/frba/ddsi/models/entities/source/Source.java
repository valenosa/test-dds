package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Source {
  @Setter
  private Long id;
  private final String baseUrl;

  public static Source from(SourceInputDTO dto) {
    return new Source(dto.getBaseUrl());
  }

  public Source(String baseUrl) {
    this.baseUrl = baseUrl;
  }
}