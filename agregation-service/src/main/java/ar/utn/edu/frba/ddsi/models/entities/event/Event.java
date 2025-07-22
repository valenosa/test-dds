package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Event {

  //-- Internal Id
  @Setter
  private Long id;

  //-- Source
  private final Source source;
  private final Long inSourceEventId;

  //-- Event Info
  private final String title;
  private final String description;
  private final Category category;
  private final Double latitude;
  private final Double longitude;
  private final LocalDateTime eventDate;

  //-- Funcionales
  public Set<Tag> tags;
  private final LocalDateTime uploadDate;
  private boolean deleted;

  public static Event from(EventInputDTO dto, Source source) {
    return new Event(
        dto.getTitle(),
        dto.getDescription(),
        new Category(dto.getCategory()), //TODO: Ver que onda esto por ahora hardcodeo pera poder continuar
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        dto.getUploadDate(),
        source,
        dto.getId()
    );
  }

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDateTime eventDate,
               LocalDateTime uploadDate,
               Source source,
               Long inSourceEventId) {
    //Information
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = uploadDate;
    this.source = source;
    this.inSourceEventId = inSourceEventId;

    //Functional
    this.deleted = false;
    tags = new HashSet<>();
  }

  public void markAsDeleted() {
    this.deleted = true;
  }

////  public boolean isFromSource(SourceKey sourceKey) {
////    return
////        Objects.equals(this.sourceId, sourceKey.getSourceId())
////            &&
////            Objects.equals(this.sourceClientId, sourceKey.getSourceClientId());
////  }

}
