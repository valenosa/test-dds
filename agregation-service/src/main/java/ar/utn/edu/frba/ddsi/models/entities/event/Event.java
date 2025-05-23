package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.EventKey;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
public class Event {

  //-- Identificador
  @Setter  private EventKey id;
  private final Long sourceId;

  //-- Description
  private final String title;
  private final String description;
  private final Category category;

  //-- Ubicacion
  private final Double latitude;
  private final Double longitude;

  //-- Fechas
  private final LocalDate eventDate;
  private final LocalDate uploadDate;

  //-- Funcionales
  private boolean deleted;
  public Set<Tag> tags;

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDate eventDate,
               Long sourceId) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = LocalDate.now();
    this.sourceId = sourceId;
    this.deleted = false;
    tags = new HashSet<>();
  }

  public void markAsDeleted() {
    this.deleted = true;
  }

}
