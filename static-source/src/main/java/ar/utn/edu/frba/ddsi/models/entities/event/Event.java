package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Event {

  //-- Identificador
  @Setter private Long id;
  @Setter private Long sourceId;
  private final Origin origin;

  //-- Description
  private final String title;
  private final String description;
  private final Category category;

  //-- Ubicacion
  private final Double latitude;
  private final Double longitude;

  //-- Fechas
  private final LocalDateTime eventDate;
  private final LocalDateTime uploadDate;

  //-- Funcionales
  private boolean deleted;

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDateTime eventDate,
               Origin origin, Long sourceId) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = LocalDateTime.now();
    this.origin = origin;
    this.sourceId = sourceId;
    this.deleted = false;
  }

  public void markAsDeleted() {
    this.deleted = true;
  }
}
