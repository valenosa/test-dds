package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.EventKey;
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
  private boolean newOrModified;
  private boolean deleted;
  public Set<Tag> tags;

  public static Event from(EventInputDTO dto){

    EventKey eventKey = new EventKey(dto.getId(),dto.getOrigin());

    Event event = new Event(
        dto.getTitle(),
        dto.getDescription(),
        new Category(dto.getCategory()), //TODO: Ver que onda esto por ahora hardcodeo pera poder continuar
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        dto.getUploadDate(),
        dto.getSourceId()
    );

    event.setId(eventKey);

    return event;
  }

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDate eventDate,
               LocalDate uploadDate,
               Long sourceId) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = uploadDate;
    this.sourceId = sourceId;
    this.newOrModified = true;
    this.deleted = false;
    tags = new HashSet<>();
  }

  public void markAsDeleted() {
    this.deleted = true;
  }

}
