package ar.edu.utn.frba.dds.domain.entities.fact;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Fact {

  //-- Description
  @Getter
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
  @Setter
  private boolean deleted;
  private final Origin origin;
  public Set<Tag> tags; //TODO pasar a private

  public Fact(String title,
              String description,
              Category category,
              Double latitude,
              Double longitude,
              LocalDate eventDate,
              LocalDate uploadDate,
              Origin origin) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = uploadDate;
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

}