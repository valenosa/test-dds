package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Event {

  //-- Identificador
  @Setter private Long id;
  private final Origin origin;

  //-- Description
  private String title;
  private String description;
  private Category category;

  //-- Ubicacion
  private Double latitude;
  private Double longitude;

  //-- Fechas
  private LocalDate eventDate;
  private final LocalDate uploadDate;

  //-- Submmision (Dynamic-Source)
  private final String contributor; //TODO: Esto deberia ser un usuario
  @Setter private SubmissionState state;
  @Setter private String suggestion;

  //-- Extras
  @Setter
  private boolean modified;
  private boolean deleted;

  public static Event from(EventCreationDTO dto){
    return new Event(
        dto.getTitle(),
        dto.getDescription(),
        dto.getCategory(),
        dto.getLatitude(),
        dto.getLongitude(),
        dto.getEventDate(),
        Origin.DYNAMIC,
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

    this.modified = true;
    this.deleted = false;
    this.state = SubmissionState.PENDING;
  }

  public void updateWith(EventUpdateDTO dto){
    this.title = dto.getTitle();
    this.description = dto.getDescription();
    this.category = dto.getCategory();
    this.latitude = dto.getLatitude();
    this.longitude = dto.getLongitude();
    this.eventDate = dto.getEventDate();
    this.setModified(true);
  }

  public void markAsModified() {this.modified = true;}

  public boolean isValid() {
    return !this.deleted && (this.state == SubmissionState.ACCEPTED || this.state == SubmissionState.ACCEPTED_WITH_SUGGESTIONS);
  }

}
