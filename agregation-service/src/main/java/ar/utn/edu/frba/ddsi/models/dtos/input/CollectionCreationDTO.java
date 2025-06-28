package ar.utn.edu.frba.ddsi.models.dtos.input;

import java.util.List;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Consensus;
import lombok.Data;

@Data
public class CollectionCreationDTO {
  String title;
  String description;
  Consensus consensus;
  List<ConditionDTO> conditions;
  List<SourceKeyDTO> sourceIds;
}
