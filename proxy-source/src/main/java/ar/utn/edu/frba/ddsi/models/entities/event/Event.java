package ar.utn.edu.frba.ddsi.models.entities.event;

import ar.utn.edu.frba.ddsi.models.dtos.input.ExternalDisasterDTO;
import ar.utn.edu.frba.ddsi.models.entities.source.IEventSource;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Event {

  @Setter
  private Long id;
  @Setter
  @Getter(AccessLevel.NONE)
  private IEventSource source;
  private final Origin origin;

  //-- Description
  private final String title;
  private final String description;
  private final Category category;

  //-- Ubicacion
  private final Double latitude;
  private final Double longitude;

  //-- Fechas
  private final LocalDateTime eventDate;
  private final LocalDateTime updateDate;

  public Event(Long id,
               String title,
               String description,
               Category category,
               Double latitude,
               Double longitude,
               LocalDateTime eventDate,
               LocalDateTime updateDate,
               Origin origin) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.category = category;
    this.latitude = latitude;
    this.longitude = longitude;
    this.eventDate = eventDate;
    this.updateDate = updateDate; //TODO Debería ser esto o created_at de la API?
    this.origin = origin;
  }

  public static Event from(ExternalDisasterDTO externalDisaster) {
    //TODO crear evento. Debería tener updated_at
    return new Event(
        externalDisaster.getId(),
        externalDisaster.getTitulo(),
        externalDisaster.getDescripcion(),
        new Category(externalDisaster.getCategoria()),
        externalDisaster.getLatitud(),
        externalDisaster.getLongitud(),
        //TODO Sacar .toLocalDateTime() y manejar correctamente (por zona horaria)
        externalDisaster.getEventDate().toLocalDateTime(),
        externalDisaster.getUpdateDate().toLocalDateTime(),
        Origin.PROXY);
  }

  public boolean isOutdated(LocalDateTime lastUpdate) {
    return this.getUpdateDate().isAfter(lastUpdate);
  }

  public Long getSourceId() {
    return source.getId();
  }
}
