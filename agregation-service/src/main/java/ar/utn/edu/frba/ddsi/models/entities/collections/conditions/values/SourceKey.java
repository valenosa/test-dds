package ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import lombok.Getter;

@Getter
public class SourceKey {
  Long sourceId;
  Origin sourceType;
}
