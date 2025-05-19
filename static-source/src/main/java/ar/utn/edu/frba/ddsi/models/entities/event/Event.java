package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
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
  private final String title;
  private final String description;
  private final Category category;

  //-- Ubicacion
  private final Double latitude;
  private final Double longitude;

  //-- Fechas
  private final LocalDate eventDate;
  private final LocalDate uploadDate;

  //-- Extras
  private boolean deleted;
  private final Origin origin;
  public Set<Tag> tags;

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
