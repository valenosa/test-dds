package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.Origin;
import lombok.Data;

@Data
public class SourceInputDTO {
  private String baseUrl;
  private Origin type;
}
