package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import lombok.Data;

@Data
public class SourceClientDTO {
  String url;
  Origin type;

  public static SourceClientDTO from(SourceClient client) {
    SourceClientDTO dto = new SourceClientDTO();
    dto.setUrl(client.getUrl());
    dto.setType(client.getType());
    return dto;
  }
}