package ar.utn.edu.frba.ddsi.models.entities.event;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Event {

  @Setter private Long id;
  @Setter private Long sourceId;
  private final Origin origin;

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

  //-- Funcionales
  @Setter private boolean modified;
  private boolean deleted;

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
    this.uploadDate = LocalDate.now(); //TODO Debería ser esto o created_at de la API?
    this.origin = origin;
    this.modified = true;
    this.deleted = false;
  }

  public void deleteEvent(){
    this.deleted = true;
  }

}
