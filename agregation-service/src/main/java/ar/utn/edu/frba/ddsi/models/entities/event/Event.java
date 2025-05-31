package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values.SourceKey;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
import java.time.LocalDateTime;
import java.util.Objects;
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
  private final Long sourceClientId;
  private final Long inSourceEventId;

  //-- Event Info
  private final String title;
  private final String description;
  private final Category category;
  private final Origin origin;
  private final Double latitude;
  private final Double longitude;
  private final LocalDateTime eventDate;

  //-- Funcionales
  public Set<Tag> tags;
  private final LocalDateTime uploadDate;
  private boolean deleted;

  public static Event from(EventInputDTO dto, Long sourceClientId) {
    return new Event(
        dto.getTitle(),
        dto.getDescription(),
        new Category(dto.getCategory()), //TODO: Ver que onda esto por ahora hardcodeo pera poder continuar
        dto.getOrigin(),
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        dto.getUploadDate(),
        sourceClientId,
        dto.getSourceId(),
        dto.getId()
    );
  }

  public Event(String title,
               String description,
               Category category,
                Origin origin,
               Double latitude,
               Double longitude,
               LocalDateTime eventDate,
               LocalDateTime uploadDate,
               Long sourceClientId,
               Long sourceId,
               Long inSourceEventId) {
    //Information
    this.title = title;
    this.description = description;
    this.category = category;
    this.origin = origin;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = uploadDate;

    //External Id
    this.sourceClientId = sourceClientId;
    this.sourceId = sourceId;
    this.inSourceEventId = inSourceEventId;

    //Functional
    this.deleted = false;
    tags = new HashSet<>();
  }

  public void markAsDeleted() {
    this.deleted = true;
  }

  public boolean isFromSource(SourceKey sourceKey) {
    return
        Objects.equals(this.sourceId, sourceKey.getSourceId())
            &&
            Objects.equals(this.sourceClientId, sourceKey.getSourceClientId());
  }

}
