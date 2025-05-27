package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import java.time.LocalDateTime;
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
  private LocalDateTime eventDate;
  private LocalDateTime uploadDate;

  //-- Submmision (Dynamic-Source)
  private final String contributor; //TODO: Esto deberia ser un usuario
  @Setter private SubmissionState state;
  @Setter private String suggestion;

  //-- Extras
  @Setter
  private boolean deleted;

  public static Event from(EventDTO dto){
    return new Event(
        dto.getTitle(),
        dto.getDescription(),
        new Category(dto.getCategory()), //TODO: Manejar Categorias
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
               LocalDateTime eventDate,
               Origin origin,
               String contributor) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.uploadDate = LocalDateTime.now();
    this.origin = origin;
    this.contributor = contributor;
    this.deleted = false;
    this.state = SubmissionState.PENDING;
  }

  public void updateWith(EventDTO dto){
    this.title = dto.getTitle();
    this.description = dto.getDescription();
    this.category = new Category(dto.getCategory()); //TODO: Manejar Categorias
    this.latitude = dto.getLatitude();
    this.longitude = dto.getLongitude();
    this.eventDate = dto.getEventDate();

    //Actualiza fecha de carga a la fecha de modificacion
    this.uploadDate = LocalDateTime.now();
  }

  public boolean isAccepted() {
    return this.state == SubmissionState.ACCEPTED || this.state == SubmissionState.ACCEPTED_WITH_SUGGESTIONS;
  }

  public boolean isPending() {
    return this.state == SubmissionState.PENDING;
  }

  public void markAsDeleted() {
    this.deleted = true;
  }
}
