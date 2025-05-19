package ar.utn.edu.frba.ddsi.models.dtos.input;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EventUpdateDTO {
  Long eventId;
  String contributor; //TODO: Esto deberia ser un usuario

  String title;
  String description;
  Category category;
  Double latitude;
  Double longitude;
  LocalDate eventDate;
}

