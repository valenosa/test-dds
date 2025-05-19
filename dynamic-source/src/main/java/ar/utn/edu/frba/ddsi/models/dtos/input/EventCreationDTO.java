package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EventCreationDTO {
  String user; //TODO: Esto deberia ser un usuario

  String title;
  String description;
  Category category;
  Double latitude;
  Double longitude;
  LocalDate eventDate;
}
