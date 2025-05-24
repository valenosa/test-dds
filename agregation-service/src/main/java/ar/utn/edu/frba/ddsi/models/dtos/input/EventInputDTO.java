package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import java.time.LocalDate;;
import lombok.Data;

@Data
public class EventInputDTO {
    Long id;
    Long sourceId;
    Origin origin;
    String title;
    String description;
    String category;
    Double latitude;
    Double longitude;
    LocalDate eventDate;
    LocalDate uploadDate;
}
