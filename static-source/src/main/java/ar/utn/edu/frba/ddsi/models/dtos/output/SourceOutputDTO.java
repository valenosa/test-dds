package ar.utn.edu.frba.ddsi.models.dtos.output;

import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;

import lombok.Data;

@Data
public class SourceOutputDTO {
  Long id;
  String path;

  public static SourceOutputDTO from(Source source) {

    SourceOutputDTO dto = new SourceOutputDTO();
    dto.setId(source.getId());
    dto.setPath(source.getPath());

    return dto;
  }


}
