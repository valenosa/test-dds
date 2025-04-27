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
  private final LocalDate dateEvent;
  private final LocalDate uploadDate;
  //-- Extras
  @Setter
  private boolean eliminated;
  private final Origin origin;
  public Set<Tag> tags; //lo pongo public para usarlo en test

  public Fact(String title,
              String description,
              Category category,
              Double latitude,
              Double longitude,
              LocalDate dateEvent,
              LocalDate uploadDate,
              Origin origin) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.dateEvent = dateEvent;
    this.uploadDate = uploadDate;
    this.origin = origin;
    this.eliminated = false;
    tags = new HashSet<>();
  }

  public void addTag(Tag tag, Tag... tagsAdded) {
    tags.add(tag);
    if (tagsAdded != null) {
      tags.addAll(Set.of(tagsAdded));
    }
  }

}