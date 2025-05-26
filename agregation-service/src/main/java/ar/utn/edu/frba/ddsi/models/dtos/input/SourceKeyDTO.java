package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import lombok.Data;

@Data
public class SourceKeyDTO {
    private Long sourceId;
    private Origin sourceType;
}
