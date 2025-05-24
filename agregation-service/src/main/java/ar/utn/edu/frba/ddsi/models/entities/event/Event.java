package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Setter;

@Getter
public class Event {

  //-- Internal Id
  @Setter private Long id;

  //-- Exteral Ids
  private final Long sourceId;
  private final Long inSourceEventId;
  private final Origin sourceEventOrigin;

  //-- Event Info
  private final String title;
  private final String description;
  private final Category category;
  private final Double latitude;
  private final Double longitude;
  private final LocalDate eventDate;

  //-- Funcionales
  public Set<Tag> tags;
  private final LocalDate uploadDate;
  private boolean deleted;

  public static Event from(EventInputDTO dto){
    return new Event(
        dto.getTitle(),
        dto.getDescription(),
        new Category(dto.getCategory()), //TODO: Ver que onda esto por ahora hardcodeo pera poder continuar
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        dto.getUploadDate(),
        dto.getSourceId(),
        dto.getId(),
        dto.getOrigin()
    );
  }

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDate eventDate,
               LocalDate uploadDate,
               Long sourceId,
               Long inSourceEventId,
               Origin sourceEventOrigin) {
    //Information
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = uploadDate;

    //External Id
    this.sourceId = sourceId;
    this.inSourceEventId = inSourceEventId;
    this.sourceEventOrigin = sourceEventOrigin;

    //Functional
    this.deleted = false;
    tags = new HashSet<>();
  }

  public void markAsDeleted() {
    this.deleted = true;
  }

}
