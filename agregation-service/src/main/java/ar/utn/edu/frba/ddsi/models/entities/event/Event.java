package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;
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
  private  String title;
  private  String description;
  private  Category category;
  private  Double latitude;
  private  Double longitude;
  private  LocalDateTime eventDate;

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

    // Update state in the origin source
    if (source.getType() != Origin.PROXY) {
      try {
        source.getSourceClient().getWebClient().delete().uri("/events/" + inSourceEventId)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
      } catch (Exception e) {
        throw new RuntimeException("Error updating event in source origin: " + e.getMessage(), e);
      }
    }

  }

  public void update(Event event) {
    //? ¿Debería validar que todos estos campos no sean null?
    this.title = event.getTitle();
    this.description = event.getDescription();
    this.category = event.getCategory();
    this.latitude = event.getLatitude();
    this.longitude = event.getLongitude();
    this.eventDate = event.getEventDate();
  }
}
