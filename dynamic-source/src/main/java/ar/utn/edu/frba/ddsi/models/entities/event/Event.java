package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
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
  private final String contributor; //TODO: Esto deberia ser un usuario
  @Setter private SubmissionState state;
  @Setter private String suggestion;


  //-- Extras
  private boolean deleted;
  private final Origin origin;
  public Set<Tag> tags;

  public static Event from(EventCreationDTO dto){
    return new Event(
        dto.getTitle(),
        dto.getDescription(),
        dto.getCategory(),
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        Origin.CONTRIBUTOR,
        dto.getContributor()
    );
  }

  public Event(String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDate eventDate,
               Origin origin,
               String contributor) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = LocalDate.now();
    this.origin = origin;
    this.contributor = contributor;

    this.deleted = false;
    this.state = SubmissionState.PENDING;
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

  public boolean valid() {
    return !this.deleted && (this.state == SubmissionState.ACCEPTED || this.state == SubmissionState.ACCEPTED_WITH_SUGGESTIONS);
  }

}
