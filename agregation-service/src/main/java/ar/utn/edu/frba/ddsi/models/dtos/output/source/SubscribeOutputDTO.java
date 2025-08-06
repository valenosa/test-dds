package ar.utn.edu.frba.ddsi.models.dtos.output.source;

import ar.utn.edu.frba.ddsi.models.entities.source.impl.SourceClient;
import lombok.Data;

@Data
public class SubscribeOutputDTO {
  Long sourceClientId;
  String callbackUrl;

  public static SubscribeOutputDTO from(SourceClient sourceClient) {
    SubscribeOutputDTO dto = new SubscribeOutputDTO();
    dto.sourceClientId = sourceClient.getId();
    dto.callbackUrl = sourceClient.getUrl();
    return dto;
  }
}
