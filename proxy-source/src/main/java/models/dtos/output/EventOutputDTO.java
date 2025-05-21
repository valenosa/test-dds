package models.dtos.output;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import models.entities.event.Event;
import models.entities.event.Origin;
import models.entities.event.Tag;


@Data
public class EventOutputDTO {
  Long id;
  String title;
  String description;
  String category;
  Double latitude;
  Double longitude;
  LocalDate eventDate;
  LocalDate uploadDate;
  Origin origin;
  Set<String> tags;

  public static EventOutputDTO from(Event event) {
    EventOutputDTO dto = new EventOutputDTO();
    dto.setId(event.getId());
    dto.setTitle(event.getTitle());
    dto.setDescription(event.getDescription());
    dto.setCategory(event.getCategory().getName()); //TODO: Verificar si esta bien pasar nombre o tenemos que pasar Id o un DTO
    dto.setLatitude(event.getLatitude());
    dto.setLongitude(event.getLongitude());
    dto.setEventDate(event.getEventDate());
    dto.setUploadDate(event.getUploadDate());
    dto.setOrigin(event.getOrigin()); //TODO: Verificar si esta bien pasar el ENUM
    dto.setTags(event.getTags().stream().map(Tag::getName).collect(Collectors.toSet())); //TODO: Verificar si esta bien pasar Set<nombre> o tenemos que pasar Set<Id> o un Set<DTO>
    return dto;
  }

}
