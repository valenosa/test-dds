package ar.utn.edu.frba.ddsi.models.dtos.input;

import java.util.List;
import lombok.Data;

@Data
public class CollectionCreationDTO {
  String title;
  String description;
  List<ConditionDTO> conditions;
  List<Long> sourceIds;
}
