package ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceKeyDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import lombok.Getter;

@Getter
public class SourceKey {
  Long sourceId;
  Long sourceClientId;

  public SourceKey(Long sourceId, Long sourceClientId) {
    this.sourceId = sourceId;
    this.sourceClientId = sourceClientId;
  }

  public static SourceKey from(SourceKeyDTO dto) {
    return new SourceKey(dto.getSourceId(), dto.getSourceClientId());
  }
}
