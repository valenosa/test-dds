package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {
  List<EventOutputDTO> getEvents(LocalDateTime lastUpdate);

  List<EventOutputDTO> getPendingEvents();

  EventOutputDTO save(EventCreationDTO dto);

  void update(EventUpdateDTO dto);

  EventOutputDTO deleteEvent(Long eventId);
}