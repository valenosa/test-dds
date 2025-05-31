 package ar.utn.edu.frba.ddsi.models.dtos.output;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Tag;
import java.time.LocalDateTime;
import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class EventOutputDTO {
  Long id;
  Long sourceClientId;
  Long sourceId;
  Origin origin;
  String title;
  String description;
  String category;
  Double latitude;
  Double longitude;
  LocalDateTime eventDate;
  LocalDateTime uploadDate;
  Set<String> tags;

  public static EventOutputDTO from(Event event) {
    EventOutputDTO dto = new EventOutputDTO();
    dto.setId(event.getId());
    dto.setSourceClientId(event.getSourceClientId());
    dto.setSourceId(event.getSourceId());
    dto.setTitle(event.getTitle());
    dto.setDescription(event.getDescription());
    dto.setCategory(event.getCategory().getName()); //TODO: Verificar si esta bien pasar nombre o tenemos que pasar Id o un DTO
    dto.setOrigin(event.getOrigin());
    dto.setLatitude(event.getLatitude());
    dto.setLongitude(event.getLongitude());
    dto.setEventDate(event.getEventDate());
    dto.setUploadDate(event.getUploadDate());
    dto.setTags(event.getTags().stream().map(Tag::getName).collect(Collectors.toSet())); //TODO: Verificar si esta bien pasar Set<nombre> o tenemos que pasar Set<Id> o un Set<DTO>
    return dto;
  }
}
