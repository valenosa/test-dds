package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.ConditionType;
import lombok.Data;

@Data
public class ConditionDTO {
  ConditionType conditionType;
  Object conditionValue; // Depende del ConditionType se castea a un tipo de dato (Category,String,LocalDate)
}
