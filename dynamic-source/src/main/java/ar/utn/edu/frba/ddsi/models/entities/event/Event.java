package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Event {

  @Setter
  private Long id;

  //-- Description
  private String title;
  private String description;
  private Category category;

  //-- Ubicacion
  private Double latitude;
  private Double longitude;

  //-- Fechas
  private LocalDate eventDate;
  private LocalDate uploadDate;

  //-- Dynamic Source Specific
  @Setter
  private String contributor; //TODO: Esto deberia ser un usuario

  //-- Extras
  private boolean deleted;
  private final Origin origin;
  public Set<Tag> tags;

  public static Event from(EventCreationDTO dto){
    Event event = new Event(
        dto.getTitle(),
        dto.getDescription(),
        dto.getCategory(),
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        Origin.CONTRIBUTOR
    );

    // TODO: Revisar tema Contributor anonimo
    event.contributor = dto.getContributor();

    return event;
  }

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDate eventDate,
               Origin origin) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = LocalDate.now();
    this.origin = origin;
    this.deleted = false;
    tags = new HashSet<>();
  }

  public void updateWith(EventUpdateDTO dto){
    this.title = dto.getTitle();
    this.description = dto.getDescription();
    this.category = dto.getCategory();
    this.latitude = dto.getLatitude();
    this.longitude = dto.getLongitude();
    this.eventDate = dto.getEventDate();
  }

  public void addTag(Tag tag, Tag... tagsAdded) {
    tags.add(tag);
    if (tagsAdded != null) {
      tags.addAll(Set.of(tagsAdded));
    }
  }

  public void markAsDeleted() {

    this.deleted = true;
  }

}
