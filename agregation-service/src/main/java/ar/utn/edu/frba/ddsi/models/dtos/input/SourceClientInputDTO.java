package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import lombok.Data;

@Data
public class SourceClientInputDTO {
  String url;
  Origin type;

  public static SourceClientInputDTO from(SourceClient client) {
    SourceClientInputDTO dto = new SourceClientInputDTO();
    dto.setUrl(client.getUrl());
    dto.setType(client.getType());
    return dto;
  }
}