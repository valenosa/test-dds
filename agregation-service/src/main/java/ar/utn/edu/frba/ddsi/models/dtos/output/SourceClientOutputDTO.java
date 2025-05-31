package ar.utn.edu.frba.ddsi.models.dtos.output;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import lombok.Data;

@Data
public class SourceClientOutputDTO {
  Long id;
  String url;
  Origin type;

  public static SourceClientOutputDTO from(SourceClient client) {
    SourceClientOutputDTO dto = new SourceClientOutputDTO();
    dto.setId(client.getId());
    dto.setUrl(client.getUrl());
    dto.setType(client.getType());
    return dto;
  }
}